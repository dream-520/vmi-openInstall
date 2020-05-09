package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserGiftBoxAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserChatGiftBoxLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftBoxContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftBoxLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxLogEntity;

/** 
  * @author mouzhanpeng at [2017年12月20日 下午4:21:50] 
  * @since JDK 1.8.0 
  */
@Service
public class UserGiftBoxAgentImpl implements IUserGiftBoxAgent {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserChatGiftBoxContract userChatGiftBoxContract;
	
	
	@Autowired
	private IUserChatGiftBoxLogContract userChatGiftBoxLogContract;
	
	
	


	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult changeGiftBoxAccount(long userId, Long otherId, long giftId, long amount,
			UserChatGiftBoxLogTypeEnum type, String transactionFlow, String memo) throws Exception {
		LOGGER.info("userid:" + userId+",otherId:" + otherId +",giftId:" + giftId+ ",amount:" + amount  + ",type:" + JsonHelper.toJson(type)  + ",transactionFlow:" + transactionFlow + ",memo:" + memo);
		Date date = new Date();
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		UserChatGiftBoxEntity fa = userChatGiftBoxContract.lockByUserId(userId,giftId);
		if(null == fa){
			fa = new UserChatGiftBoxEntity();
			fa.setCreate_time(date);
			fa.setUpdate_time(date);
			fa.setUser_id(userId);
			fa.setGift_id(giftId);
			fa.setAmount(0L);
			fa.setStatus(1);
			userChatGiftBoxContract.insert(fa);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type.getIo().getCode()));
		if (userChatGiftBoxLogContract.count(pageModel) != 0) {
			return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == type.getIo().getCode() && fa.getAmount() < amount) {
			return AgentResult.fail(AgentErrorCodeEnum.not_enough);
		}
		// 更新账户表的信息
		StringBuilder buf = new StringBuilder("amount=amount").append(type.getIo().getCode() == 1 ? "+" : "-").append(amount).append(",update_time=now()");
		
		userChatGiftBoxContract.updateByStatement(buf.toString(), "id=" + fa.getId());

		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		UserChatGiftBoxLogEntity logEntity = new UserChatGiftBoxLogEntity();
		logEntity.setCreate_time(date);
		logEntity.setUser_id(userId);
		logEntity.setOther_id(Tools.isNotNull(otherId)?otherId:0);
		logEntity.setGift_id(giftId);
		logEntity.setAmount(amount);
		logEntity.setLog_type(type.getIo().getCode());
		logEntity.setBalance(fa.getAmount() + (type.getIo().getCode() == 0 ? -amount : amount));
		logEntity.setType(type.getCode());
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		userChatGiftBoxLogContract.insert(logEntity);
		return AgentResult.success(logEntity.getBalance());
	}
	
	
	
	
}
