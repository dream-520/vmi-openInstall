package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.shark.miai.common.enums.BussinessMessageTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IMessageRedDotAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserScvcAgent;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.ScvcAwardCategoryEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.inter.contract.IUserScvcAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserScvcAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountLogEntity;

/** 
  * @author mouzhanpeng at [2018年1月24日 下午6:59:35] 
  * @since JDK 1.8.0 
  */
@Service
public class UserScvcAgentImpl implements IUserScvcAgent {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserScvcAccountContract userScvcAccountContract;
	
	@Autowired
	private IUserScvcAccountLogContract userScvcAccountLogContract;
	
	@Autowired
	private INewPushAgent newPushAgent; 
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IMessageRedDotAgent messageRedDotAgent;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult changeScvcAccount(long userId, int amount, int io, int type, String transactionFlow, String memo) throws Exception {
		LOGGER.info("changeScvcAccount-[userId:" + userId + ",amount" + amount + ",io" + io + ",type" + type + ",transactionFlow" + transactionFlow + ",memo" + memo +"]");
		if(0 >= userId || 0 >= amount){
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		UserScvcAccountEntity account = userScvcAccountContract.lockByUserId(userId);
		Date date = new Date();
		if(null == account){
			account = new UserScvcAccountEntity();
			account.setCreate_time(date);
			account.setUpdate_time(date);
			account.setUser_id(userId);
			account.setBalance(0L);
			account.setConsume(0L);
			account.setDeposit(0L);
			account.setStatus(1);
			userScvcAccountContract.insert(account);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		if (userScvcAccountLogContract.count(pageModel) != 0) {
			return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == io && account.getBalance() < amount) {
			return AgentResult.fail(AgentErrorCodeEnum.not_enough);
		}
		account.setUpdate_time(date);
		if(1 == io){
			account.setBalance(account.getBalance() + amount);
			account.setDeposit(account.getDeposit() + amount);
		}else{
			account.setBalance(account.getBalance() - amount);
			account.setConsume(account.getConsume() + amount);
		}
		userScvcAccountContract.update(account);
		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		UserScvcAccountLogEntity logEntity = new UserScvcAccountLogEntity();
		logEntity.setCreate_time(date);
		logEntity.setUser_id(userId);
		logEntity.setBalance(account.getBalance().intValue());
		logEntity.setAmount(amount);
		logEntity.setIo(io);
		logEntity.setType(type);
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		userScvcAccountLogContract.insert(logEntity);
		return AgentResult.success(account.getBalance());
	}

	@Override
	public AgentResult gainScvcVia(long userId, ScvcAwardCategoryEnum category, String transactionFlow, String memo) throws Exception {
		return AgentResult.success();
		/*switch (category) {
		case RECHARGE:
		case VIDEO:
			AgentResult result = changeScvcAccount(userId, category.getAward(), 1, category.getCode(), transactionFlow, memo);
			pushMsg(userId, category);
			return result;
		case ONLINE:
		case BANG:
		case SKILL:
			//每天只奖励一次
			PageModel pageModel = PageModel.getLimitModel(0, 1);
			pageModel.addQuery(Restrictions.eq("user_id", userId));
			pageModel.addQuery(Restrictions.eq("type", category.getCode()));
			pageModel.addQuery(Restrictions.gt("create_time", Tools.getDateTime(Tools.getDayTime())));
			if (userScvcAccountLogContract.count(pageModel) != 0) {
				return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
			}else{
				result = changeScvcAccount(userId, category.getAward(), 1, category.getCode(), transactionFlow, memo);
				pushMsg(userId, category);
				return result;
			}
//		case LUCKY:
//			return changeScvcAccount(userId, category.getAward(), 1, category.getCode(), transactionFlow, memo);
		default:
			throw new IllegalArgumentException("No enumed object matched!");
		}*/
	}

	/**
	 * 推送业务消息
	 * @param userId
	 * @param category
	 * @throws Exception
	 */
	private void pushMsg(long userId, ScvcAwardCategoryEnum category) throws Exception{
		PushParamDto param = PushHelper.getPushParamDto(userAgent.findById(userId), PushTypeEnum.default_type, PushContentTypeEnum.necessary, NewPushAppTagEnum.bussiness_msg_fragment);
		param.setTitle(category.getTitle());
		param.setContent(String.format(category.getMsg(), category.getAward()));
		messageRedDotAgent.addBussinessMessage(userId, e2e(category), null);
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	private BussinessMessageTypeEnum e2e(ScvcAwardCategoryEnum sace){
		switch (sace) {
		case RECHARGE: return BussinessMessageTypeEnum.scvc_award_daily_recharge;
		case VIDEO: return BussinessMessageTypeEnum.scvc_award_daily_video;
		case ONLINE: return BussinessMessageTypeEnum.scvc_award_daily_online;
		case BANG: return BussinessMessageTypeEnum.scvc_award_daily_bang;
		case SKILL: return BussinessMessageTypeEnum.scvc_award_daily_skill;
		default:
			return null;
		}
	}
	
	@Override
	public long getScvcBalance(long userId) throws Exception {
		UserScvcAccountEntity account = userScvcAccountContract.lockByUserId(userId);
		return Tools.isNotNull(account) ? account.getBalance() : 0;
	}

	@Override
	public long getScvcDeposit(long userId) throws Exception {
		UserScvcAccountEntity account = userScvcAccountContract.lockByUserId(userId);
		return Tools.isNotNull(account) ? account.getDeposit() : 0;
	}
}
