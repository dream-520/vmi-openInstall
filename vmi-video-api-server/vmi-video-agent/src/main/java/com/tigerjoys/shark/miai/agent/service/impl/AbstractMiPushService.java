package com.tigerjoys.shark.miai.agent.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tigerjoys.shark.miai.agent.service.INewPushService;
import com.xiaomi.push.sdk.ErrorCode;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.Subscription;
import com.xiaomi.xmpush.server.TargetedMessage;

public abstract class AbstractMiPushService implements INewPushService {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractMiPushService.class);
	
	// 默认离线消息有效时间
	private static final long default_expire = 24 * 3600 * 1000;
	
	//使用ios还是安卓的小米推送常量声明
	protected abstract void useMiPush();
	
	//初始化发送推送消息的Sender
	protected abstract Sender initSender();
	
	//初始化订阅主题标签对象
	protected abstract Subscription initSubscription();
	
	//构建要发送的消息体
	protected abstract Message buildMessage(String msg, long sec, String msgType,int notifyid);
	
	//具体实现子类名称,用于日志打印
	protected abstract String implName();
	
	@Override
	public int sendMessage(String clientId, String msg, long sec, String msgType, int notifyid) {
		if (sec == -1) {
			sec = default_expire;
		}
		int result = -1;
		if ("1".equals(msgType)) { // 透传
			result = sendMsg(clientId, msg, sec, "1",notifyid);
		} else if ("2".equals(msgType)) { // 通知
			result = sendMsg(clientId, msg, sec, "0",notifyid);
		} else if ("0".equals(msgType)) { // 通知加透传
			result = sendMsg(clientId, msg, sec, "0",notifyid);
			result = sendMsg(clientId, msg, sec, "1",notifyid);
		}

		return result;
	}
	
	private int sendMsg(String clientId, String msg, long sec, String msgType,int notifyid) {

		int result = -1;
		if (StringUtils.isEmpty(clientId)) {
			return result;
		}

		try {
			//使用小米推送声明
			useMiPush();
			//初始化sender
			Sender sender = initSender();

			List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
			TargetedMessage targetedMessage1 = new TargetedMessage();
			targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, clientId);
			Message message1 = null;
			Result resultMsg = null;
			
			//构建Message消息体
			message1 = buildMessage(msg, sec, msgType,notifyid);
			targetedMessage1.setMessage(message1);
			messages.add(targetedMessage1);
			resultMsg = sender.send(messages, 0); // 根据alias，发送消息到指定设备上，不重试。
			
			//打印发送结果日志
			result = getSendResultAndPrintLogger(resultMsg, clientId, msg);

		} catch (Exception e) {
			logger.info("msg=" + msg + "发送异常", e);
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @param resultMsg
	 * @param clientId 手机在小米推送注册的regId
	 * @param msg 推送消息的消息体
	 * @param implName 具体实现bean名称
	 * @return
	 */
	private int getSendResultAndPrintLogger(Result resultMsg,String clientId,String msg) {
		int result = -1;
		if (resultMsg == null) {
			logger.info("class=" + implName() + ";getResultCode()=NULL;clientId=" + clientId + ";msg=" + msg);
			result = -1;
		}
		logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().getValue() + ";desc="
				+ resultMsg.getReason() + ";clientId=" + clientId + ";msg=" + msg);
		if (ErrorCode.Success.equals(resultMsg.getErrorCode())) {
			logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().toString()
					+ ";desc=" + resultMsg.getReason() + ";clientId=" + clientId + ";msg=" + msg + ";info=发送成功");
			result = 1;
		} else if (ErrorCode.UserNotAuthorizeApplication.equals(resultMsg.getErrorCode())) {
			logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().toString()
					+ ";desc=" + resultMsg.getReason() + ";clientId=" + clientId + ";msg=" + msg + ";info=用户没注册失败");
			result = 2;
		} else {
			logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().toString()
					+ ";desc=" + resultMsg.getReason() + ";clientId=" + clientId + ";msg=" + msg + ";info=发送失败");
			result = -1;
		}
		
		return result;
	}

	@Override
	public boolean groupPushMessageToApp(String msg, long sec, String msgType, int notifyid) {
		try {
			useMiPush();
			Message message = null;
			Result resultMsg = null;
			message = buildMessage(msg, sec, msgType, notifyid);
			resultMsg = this.getResult(initSender() , message);
			return resloveResponse(resultMsg, msg);
		} catch (Exception e) {
			logger.info("msg=" + msg + "发送异常", e);
		}

		return false;
	}
	
	/**
	 * 解析是否推送成功
	 * 
	 * @param ret
	 *            - IPushResult
	 * @return boolean
	 */
	private boolean resloveResponse(Result resultMsg, String msg) {
		if (resultMsg == null) {
			logger.info("class=" + implName() + ";getResultCode()=NULL;msg=" + msg);
			return false;
		}
		logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().getValue() + ";desc="
				+ resultMsg.getReason() + ";msg=" + msg);
		if (ErrorCode.Success.equals(resultMsg.getErrorCode())) {
			logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().toString() + ";desc="
					+ resultMsg.getReason() + ";msg=" + msg + ";info=发送成功");
			return true;
		} else if (ErrorCode.UserNotAuthorizeApplication.equals(resultMsg.getErrorCode())) {
			logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().toString() + ";desc="
					+ resultMsg.getReason() + ";msg=" + msg + ";info=用户没注册失败");
			return false;
		} else {
			logger.info("class=" + implName() + ";getResultCode()=" + resultMsg.getErrorCode().toString() + ";desc="
					+ resultMsg.getReason() + ";msg=" + msg + ";info=发送失败");
			return false;
		}
	}
	
	/**
	 * 获得发送结果
	 * @param sender
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private Result getResult(Sender sender, Message message) throws IOException, ParseException {
		return sender.broadcastAll(message, 0);
	}

	@Override
	public void subscribeTopicByAlias(String topic, List<String> aliases) throws Exception {
	    useMiPush();
	    Subscription subscription = initSubscription();
	    subscription.subscribeTopicByAlias(topic, aliases, null, 3);
	}

	@Override
	public boolean pushMessageToAppByTopic(String topic, String msg, long sec, String msgType, int notifyid) {
		try {
			useMiPush();
			Message message = null;
			Result resultMsg = null;
			message = buildMessage(msg, sec, msgType, notifyid);
			resultMsg = this.getResult(topic,initSender() , message);
			return resloveResponse(resultMsg, msg);
		} catch (Exception e) {
			logger.info("topic:" + topic + "的msg=" + msg + "发送异常", e);
		}
		return false;
	}
	
	/**
	 * 获得发送结果
	 * @param sender
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private Result getResult(String topic,Sender sender, Message message) throws IOException, ParseException {
		return sender.broadcast(message,topic, 3);
	}

	@Override
	public void unsubscribeTopicByAlias(String topic, List<String> aliases) throws Exception {
	    useMiPush();
	    Subscription subscription = initSubscription();
	    subscription.unsubscribeTopicByAlias(topic, aliases, null, 0);
	}

}
