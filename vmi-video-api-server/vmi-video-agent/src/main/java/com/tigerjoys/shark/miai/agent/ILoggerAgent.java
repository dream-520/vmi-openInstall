package com.tigerjoys.shark.miai.agent;

import java.util.Map;

import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;

/**
 * 日志代理类
 * @author chengang
 *
 */
public interface ILoggerAgent {
	
	
	/**
	 * 记录用户现金增减的日志
	 * @param userid - 用户ID
	 * @param type - 更新类型枚举
	 * @param numeric - 本次更新的值
	 * @param balance - 本次更新后的值
	 * @param transactionFlow - 流水单号
	 * @param extraMemo - 额外备注
	 */
	public void cashLogger(long userid , int type , long numeric , long balance , String transactionFlow , String extraMemo);
	
	/**
	 * 支付宝或微信支付日志
	 * @param userId - 用户ID
	 * @param logType - 日志类型
	 * @param isreq - 是否是请求
	 * @param params - 请求参数
	 */
	public void payParamsLogger(Long userId , String logType, boolean isreq, Map<String,String> params);
	
	/**
	 * 支付中心支付请求日志
	 * @param userId - 用户ID
	 * @param logType - 日志类型
	 * @param isreq - 是否是请求
	 * @param o - 请求参数
	 */
	public void payParamsLogger(Long userId , String logType , boolean isreq , Object o);
	
	/**
	 * 记录用付费约账户增减的日志
	 * @param userid - 用户ID
	 * @param logType - 更新类型枚举
	 * @param numeric - 本次更新的值
	 * @param balance - 本次更新后的值
	 * @param transactionFlow - 流水单号
	 * @param extraMemo - 额外备注
	 */
	public void paidAppointAccountLogger(long userid , UserBalanceAccountLogTypeEnum logType , long numeric , long balance , String transactionFlow , String extraMemo);
	
	/**
	 * 网易聊天消息同步
	 * @param json
	 */
	public void chatMessageLogger(String json);
}
