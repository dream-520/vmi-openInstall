package com.tigerjoys.shark.miai.agent.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.ILoggerAgent;
import com.tigerjoys.shark.miai.agent.constant.LoggerAgentConst;
import com.tigerjoys.shark.miai.agent.enums.AccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;

/**
 * 日志代理实现类
 * @author chengang
 *
 */
@Service
public class LoggerAgentImpl implements ILoggerAgent {
	
	/**
	 * 现金更改日志
	 */
	private final Logger cashLogger = LoggerFactory.getLogger(LoggerAgentConst.USER_CASH_LOGGER_NAME);
	
	/**
	 * 充值相关日志
	 */
	private final Logger payLogger = LoggerFactory.getLogger(LoggerAgentConst.USER_PAY_LOGGER_NAME);
	
	
	/**
	 * 用户付费约账户相关日志
	 */
	private final Logger paidAppointAccountLogger = LoggerFactory.getLogger(LoggerAgentConst.USER_PAID_APPOINT_ACCOUNT_LOGGER_NAME);

	/**
	 * 用户网易聊天消息同步日志
	 */
	private final Logger neteaseChatLogger = LoggerFactory.getLogger(LoggerAgentConst.USER_NETEASE_CHAT_MESSAGE_LOGGER_NAME);

	@Override
	public void cashLogger(long userid, int type, long numeric, long balance , String transactionFlow , String extraMemo) {
		AccountLogTypeEnum logTypeEnum = AccountLogTypeEnum.getByCode(type);
		
		StringBuilder buf = new StringBuilder(logTypeEnum.getDesc());
		buf.append("|userid:").append(userid);
		buf.append("|type:").append(logTypeEnum.getCode());
		buf.append("|numeric:").append(numeric);
		buf.append("|balance:").append(balance);
		buf.append("|tsflow:").append(transactionFlow);
		buf.append("|memo:").append(extraMemo == null ? Tools.UNKNOWN:extraMemo);
		
		cashLogger.info(buf.toString());
	}

	@Override
	public void payParamsLogger(Long userId, String logType, boolean isreq, Map<String, String> params) {
		StringBuilder buf = new StringBuilder();
		if(isreq){
			buf.append("<REQUEST>|");
		}else {
			buf.append("<RESPONSE>|");
		}
		buf.append("["+logType+"]");
		if(Tools.isNotNull(userId)){
			buf.append("["+userId.longValue()+"]");
		}
		buf.append("|");
		buf.append(JsonHelper.toJson(params));
		
		payLogger.info(buf.toString());
	}
	
	@Override
	public void payParamsLogger(Long userId , String logType , boolean isreq , Object o) {
		StringBuilder buf = new StringBuilder();
		if(isreq){
			buf.append("<REQUEST>|");
		}else {
			buf.append("<RESPONSE>|");
		}
		buf.append("["+logType+"]");
		if(Tools.isNotNull(userId)){
			buf.append("["+userId.longValue()+"]");
		}
		buf.append("|");
		buf.append(JsonHelper.toJson(o));
		
		payLogger.info(buf.toString());
	}

	@Override
	public void paidAppointAccountLogger(long userid, UserBalanceAccountLogTypeEnum logType, long numeric, long balance, String transactionFlow, String extraMemo) {
		StringBuilder buf = new StringBuilder(logType.getDesc());
		buf.append("|userid:").append(userid);
		buf.append("|type:").append(logType.getCode());
		buf.append("|numeric:").append(numeric);
		buf.append("|balance:").append(balance);
		buf.append("|tsflow:").append(transactionFlow);
		buf.append("|memo:").append(extraMemo == null ? Tools.UNKNOWN:extraMemo);
		
		paidAppointAccountLogger.info(buf.toString());
	}

	@Override
	public void chatMessageLogger(String json) {
		neteaseChatLogger.info(json);
	}
}
