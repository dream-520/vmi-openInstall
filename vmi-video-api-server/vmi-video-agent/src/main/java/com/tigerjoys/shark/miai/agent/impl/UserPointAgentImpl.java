/**
 * 
 */
package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.dto.result.PointResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserPointAccountLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTaskContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTaskLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTaskEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTaskLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserPointAccountMapper;

/**
 * ClassName: UserPointAgentImpl <br/>
 * date: 2019年9月6日 下午6:11:19 <br/>
 * 
 * @author lipeng
 * @version
 * @since JDK 1.8.0
 */
@Service
public class UserPointAgentImpl implements IUserPointAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserPointAccountLogContract userPointAccountLogContract;

	@Autowired
	private UserPointAccountMapper userPointAccountMapper;
	
	@Autowired
	private IUserTaskLogContract userTaskLogContract;
	
	@Autowired
	private IUserTaskContract userTaskContract;
	
	@Override
	public PointResultDto<Long> changePointAccount(long userid, int type, int logType, String transactionFlow,String memo) throws Exception {
		try {
			UserTaskEntity userTask = userTaskContract.findByProperty("uni_code", type);
			if (userTask != null) {
				return changePointAccount(userid, userTask.getPoint(), type, 1, transactionFlow, memo);
			}
		} catch (Exception e) {
			logger.info("changePointAccount,任务出错",e);
		}
		return PointResultDto.success(0L);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public PointResultDto<Long> changePointAccount(long userid, long point, int type, int logType, String transactionFlow, String memo) throws Exception {
		logger.info("userid:" + userid + ",point:" + point + ",type:" + type + ",logType:" + logType + ",transactionFlow:" + transactionFlow + ",memo:" + memo);
		Date currDate = new Date();
		if (userid <= 0 || point <= 0 || transactionFlow == null) {
			return PointResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		UserTaskEntity task =  userTaskContract.findByProperty("uni_code", type);
		UserTaskLogEntity temp = null;
		if (task!=null) {
			PageModel pageModel = PageModel.getLimitModel(0, 1);
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("taskid", task.getId()));
			List<UserTaskLogEntity> logList = userTaskLogContract.load(pageModel);
			if (Tools.isNotNull(logList)) {
				temp = logList.get(0);
				if (temp.getCount()>=task.getCount()) {
					return PointResultDto.success(0L);
				}
			}
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		long balance = 0;
		UserPointAccountEntity account = userPointAccountMapper.lockByUserid(userid);
		if (account == null) {
			account = new UserPointAccountEntity();
			account.setCreate_time(currDate);
			account.setUpdate_time(currDate);
			account.setUserid(userid);
			account.setDeposit(0L);
			account.setConsume(0L);
			account.setBalance(0L);
			account.setStatus(0);
			userPointAccountMapper.insert(account);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		if (userPointAccountLogContract.count(pageModel) != 0) {
			return PointResultDto.fail(AgentErrorCodeEnum.repeate_record.getCode());
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == logType && account.getBalance() < point) {
			return PointResultDto.fail(AgentErrorCodeEnum.not_enough.getCode());
		}
		// 更新账户表的信息
		StringBuilder buf = new StringBuilder("balance=balance").append(logType == 1 ? "+" : "-").append(point).append(",update_time=now()");
		if (logType == 1) {// 充值
			buf.append(",deposit=deposit+");
		} else {// 消费
			buf.append(",consume=consume+");
		}
		buf.append(point);
		userPointAccountMapper.updateByStatement(buf.toString(), "id=" + account.getId());
		
		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		UserPointAccountLogEntity logEntity = new UserPointAccountLogEntity();
		logEntity.setCreate_time(currDate);
		logEntity.setUserid(userid);
		logEntity.setPoint(point);
		logEntity.setBalance(account.getBalance() + (logType == 0 ? -point : point));
		logEntity.setLog_type(logType);
		logEntity.setType(type);
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		userPointAccountLogContract.insert(logEntity);
		balance = logEntity.getBalance();
	
		//增加任务完成数量统计
		if (task!=null) {
			if (Tools.isNotNull(temp)) {
				UserTaskLogEntity log = new UserTaskLogEntity();
				log.setId(temp.getId());
				log.setCount(temp.getCount()+1);
				userTaskLogContract.update(log);
			}else{
				UserTaskLogEntity log = new UserTaskLogEntity();
				log.setUserid(userid);
				log.setCreate_time(currDate);
				log.setUpdate_time(currDate);
				log.setTaskid(task.getId());
				log.setCount(1);
				userTaskLogContract.insert(log);
			}
		}
		return PointResultDto.success(balance);
	}

	@Override
	public long getPointBalance(long userId) {
		UserPointAccountEntity entity = userPointAccountMapper.findByProperty("userid", userId);
		return Tools.isNull(entity) ? 0L : entity.getBalance();
	}
	
	@Override
	public long getPointDeposit(long userId) {
		UserPointAccountEntity entity = userPointAccountMapper.findByProperty("userid", userId);
		return Tools.isNull(entity) ? 0L : entity.getDeposit();
	}

}
