package com.tigerjoys.shark.miai.agent.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ITaskAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserScvcAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.ScvcAwardCategoryEnum;
import com.tigerjoys.shark.miai.agent.enums.TaskCategoryEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.inter.contract.ITaskContract;
import com.tigerjoys.shark.miai.inter.contract.ITaskUserCompleteContract;
import com.tigerjoys.shark.miai.inter.entity.TaskEntity;
import com.tigerjoys.shark.miai.inter.entity.TaskUserCompleteEntity;

/**
 * @author mouzhanpeng at [2017年11月27日 下午3:58:18]
 * @since JDK 1.8.0
 */
@Service
public class TaskAgentImpl implements ITaskAgent {

	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IUserScvcAgent userScvcAgent;

	@Autowired
	private ITaskUserCompleteContract taskUserCompleteContract;

	@Autowired
	private ITaskContract taskContract;

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult finishTask(long userId, TaskCategoryEnum category) throws Exception {
		if (category == TaskCategoryEnum.TALENT || category == TaskCategoryEnum.CHAT) {
			throw new IllegalArgumentException("[" + category + "] is not supported !");
		}
		TaskEntity task = detectTask(userAgent.findById(userId).getWaiterLevelId(), category);
		if (null == task) {
			return AgentResult.fail(AgentErrorCodeEnum.task_not_in_time);
		}
		// 可走redis优化，需要lua脚本支持，复杂
		long count = 0;
		if (category == TaskCategoryEnum.INFORMATION || category == TaskCategoryEnum.REGISTER) {
			if (task.getDay_times() <= (count = taskUserCompleteContract.timesLocked(userId, task.getId(), "2001-01-01 01:01:01"))) {
				return AgentResult.fail(AgentErrorCodeEnum.task_over_out_times);
			}
		} else {
			if (task.getDay_times() <= (count = taskUserCompleteContract.timesLocked(userId, task.getId(), Tools.getDateTime(Tools.getDayTime())))) {
				return AgentResult.fail(AgentErrorCodeEnum.task_over_out_times);
			}
		}
		// 完成任务
		boolean completed = (++count == task.getDay_times());
		TaskUserCompleteEntity complete = new TaskUserCompleteEntity();
		complete.setCreate_time(new Date());
		complete.setTask_id(task.getId());
		complete.setCode(task.getCode());
		complete.setUser_id(userId);
		complete.setAward(task.getAward());
		complete.setVacuate(0);
		complete.setScvc(task.getScvc());
		complete.setDirection(task.getDirection());
		complete.setCompleted(completed ? 1 : 0);
		taskUserCompleteContract.insert(complete);
		if (completed) {
			userIncomeAgent.changeIncomeAccount(userId, complete.getAward(), 1, satake(AccountType.getByCode(task.getDirection())),	String.valueOf(complete.getId()), "任务奖励收益");
			userScvcAgent.changeScvcAccount(userId, task.getScvc(), 1, ScvcAwardCategoryEnum.TASK.getCode(), String.valueOf(complete.getId()), ScvcAwardCategoryEnum.TASK.getTitle());
		}
		return AgentResult.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult finishChatTask(long from, long to) throws Exception {
		TaskEntity task = detectTask(userAgent.findById(from).getWaiterLevelId(), TaskCategoryEnum.CHAT);
		if(null == task){
			return AgentResult.fail(AgentErrorCodeEnum.task_not_in_time);
		}
		long count = 0;
		if(task.getDay_times() <= (count = taskUserCompleteContract.timesLocked(from, task.getId(), Tools.getDateTime(Tools.getDayTime())))){
			return AgentResult.fail(AgentErrorCodeEnum.task_over_out_times);
		}
		if(1 == cacheRedis.sadd(AgentRedisCacheConst.TASK_CHAT_CHECK, from + "#" + to)){
			// 次日凌晨过期
			cacheRedis.expireAt(AgentRedisCacheConst.TASK_CHAT_CHECK, Tools.getNextDayTime(1) / 1000);
			// 完成任务
			boolean completed = (++count == task.getDay_times());
			TaskUserCompleteEntity complete = new TaskUserCompleteEntity();
			complete.setCreate_time(new Date());
			complete.setTask_id(task.getId());
			complete.setCode(task.getCode());
			complete.setUser_id(from);
			complete.setAward(task.getAward());
			complete.setVacuate(0);
			complete.setScvc(task.getScvc());
			complete.setDirection(task.getDirection());
			complete.setCompleted(completed ? 1 : 0);
			taskUserCompleteContract.insert(complete);
			if (completed) {
				userIncomeAgent.changeIncomeAccount(from, complete.getAward(), 1, satake(AccountType.getByCode(task.getDirection())), String.valueOf(complete.getId()), "任务奖励收益");
				userScvcAgent.changeScvcAccount(from, task.getScvc(), 1, ScvcAwardCategoryEnum.TASK.getCode(), String.valueOf(complete.getId()), ScvcAwardCategoryEnum.TASK.getTitle());
			}
		}else{
			return AgentResult.fail(AgentErrorCodeEnum.task_chat_repeatable);
		}
		return AgentResult.success();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	@Deprecated
	public AgentResult finishTalentTask(long userId, long appointMoney, long orderId, VacuateConfigDto vacuate) throws Exception {
		/*TaskEntity task = detectTask(userAgent.findById(userId).getWaiterLevelId(), TaskCategoryEnum.TALENT);
		if(null == task){
			return AgentResult.fail(AgentErrorCodeEnum.task_not_in_time);
		}
		long count = 0;
		if(task.getDay_times() <= (count = taskUserCompleteContract.timesLocked(userId, task.getId(), Tools.getDateTime(Tools.getDayTime())))){
			return AgentResult.fail(AgentErrorCodeEnum.task_over_out_times);
		}
		// 完成任务
		boolean completed = (++count == task.getDay_times());
		TaskUserCompleteEntity complete = new TaskUserCompleteEntity();
		complete.setCreate_time(new Date());
		complete.setTask_id(task.getId());
		complete.setCode(task.getCode());
		complete.setUser_id(userId);
		complete.setAward((int)Math.round(appointMoney * task.getAward() / 100.0));
		complete.setVacuate(task.getAward());
		complete.setScvc(task.getScvc());
		complete.setDirection(task.getDirection());
		complete.setCompleted(completed ? 1 : 0);
		complete.setExtension(String.valueOf(orderId));
		taskUserCompleteContract.insert(complete);
		if (completed) {
			// 修改分成配置
			vacuate.setPlatformRatio(vacuate.getPlatformRatio() > complete.getVacuate() ? (vacuate.getPlatformRatio() - complete.getVacuate()) : 0);
			vacuate.setTaskRatio(complete.getVacuate());
			userIncomeAgent.changeIncomeAccount(userId, complete.getAward(), complete.getAward(), 1, satake(AccountType.getByCode(task.getDirection())), String.valueOf(complete.getId()), vacuate, "[达人约额外分成]任务奖励收益");
			userScvcAgent.changeScvcAccount(userId, task.getScvc(), 1, ScvcAwardCategoryEnum.TASK.getCode(), String.valueOf(complete.getId()), ScvcAwardCategoryEnum.TASK.getTitle());
		}*/
		return AgentResult.success();
	}

	/**
	 * 任务奖励去向
	 * @param direction
	 * @return
	 */
	private UserIncomeAccountLogTypeEnum satake(AccountType direction){
		return 3 == direction.getCode() ? UserIncomeAccountLogTypeEnum.bonus_award : 2 == direction.getCode() ? UserIncomeAccountLogTypeEnum.task_award : UserIncomeAccountLogTypeEnum.general_award;
	}
	
	@Override
	public TaskEntity detectTask(int level, TaskCategoryEnum category) throws Exception {
		PageModel pm = PageModel.getLimitModel(0, 1);
		pm.addQuery(Restrictions.eq("code", category.getCode()));
		pm.addQuery(Restrictions.eq("status", 1));
		// 此两种只针对普通用户，且只有一次
		if(category != TaskCategoryEnum.INFORMATION && category != TaskCategoryEnum.REGISTER){
			pm.addQuery(Restrictions.eq("level", level));
		}
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		pm.addQuery(Restrictions.le("start", time));
		pm.addQuery(Restrictions.ge("end", time));
		List<TaskEntity> tasks = taskContract.load(pm);
		return Tools.isNotNull(tasks) ? tasks.get(0) : null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> withdrawalMoney(int money, long userId, String nickname, String name, String account) throws Exception {
		IncomeResultDto<Map<String, Long>> withdrawalMoney = userIncomeAgent.withdrawalMoney(money, userId, nickname, 2, UserIncomeAccountLogTypeEnum.task_withdrawal, name, account);
		if(AgentErrorCodeEnum.success.getCode() == withdrawalMoney.getCode()){
			return IncomeResultDto.success(withdrawalMoney.getData().get("balance"));
		}else{
			return IncomeResultDto.fail(withdrawalMoney.getCode());
		}
	}

	@Override
	public IncomeResultDto<Long> withdrawalRollback(long amount, long userId, String transactionFlow, String memo) throws Exception {
		return userIncomeAgent.withdrawalRollback(amount, userId, UserIncomeAccountLogTypeEnum.task_return, transactionFlow, memo);
	}

	@Override
	public long balanceWithdrawalAlone(long userId) throws Exception {
		return userIncomeAgent.getIncomeBalance(userId, AccountType.TASK);
	}

	@Override
	public long depositWithdrawalAlone(long userId) throws Exception {
		return userIncomeAgent.getIncomeDeposit(userId, AccountType.TASK);
	}
}
