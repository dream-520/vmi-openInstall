package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.UserBalanceAccountBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.exception.PaidAppointAccountException;

/**
 * 付费约专属余额账户接口
 * @author chengang
 *
 */
public interface IUserBalanceAccountAgent {
	
	/**
	 * 用户现金账户更新并记录日志
	 * @param userid - 用户ID
	 * @param amount - 增加的数量
	 * @param logType - UserBalanceAccountLogTypeEnum
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return AgentResult data存储变更后的余额
	 * @throws PaidAppointAccountException
	 * @throws Exception
	 */
	public AgentResult changeAccount(long userid, long amount, UserBalanceAccountLogTypeEnum logType, String transactionFlow, String memo) throws PaidAppointAccountException , Exception;
	
	/**
	 * 获取账户信息
	 * @param userId - 用户ID
	 * @return PaidAppointAccountBO 有可能会是null
	 * @throws Exception
	 */
	public UserBalanceAccountBO getAccountByUserId(long userId) throws Exception;
	
	/**
	 * 获取账户剩余的余额
	 * @param userId - 用户ID
	 * @return long - 单位分
	 * @throws Exception
	 */
	public long getBalanceByUserId(long userId) throws Exception;

	/**
	 * 是否订单（取消的订单）退款到余额
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public boolean refundFromOrder(long orderId) throws Exception;

}
