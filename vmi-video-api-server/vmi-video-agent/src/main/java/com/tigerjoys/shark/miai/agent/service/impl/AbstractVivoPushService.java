package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.PushChannel;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.service.INewPushService;
import com.vivo.push.sdk.notofication.Message;
import com.vivo.push.sdk.notofication.Result;
import com.vivo.push.sdk.server.Sender;
/**
 * Vivo推送
 * @author yangjunming
 * 参考 {@link https://dev.vivo.com.cn/documentCenter/doc/197 }
 */
public abstract class AbstractVivoPushService implements INewPushService {
	
	private  final Logger logger = LoggerFactory.getLogger(getClass());
	
	public abstract String getAppSecret();
	
	public abstract int getAppId();
	
	public abstract String getAppKey();
	
	public abstract CacheRedis getCacheredis();
	
	public abstract IUserAgent getUserAgent();

	@Override
	public int sendMessage(String clientId, String msg, long sec, String msgType, int notifyid) {
		int result = -1;
		try {
			result = sendMsg(clientId, msg, sec/1000, null, notifyid);
		} catch (Exception e) {
			logger.info("vivoPushLog:发送失败 notifyid:"+notifyid+";clientId:"+clientId,e);
		}
		return result;
	}

	@Override
	public boolean groupPushMessageToApp(String msg, long sec, String msgType, int notifyid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void subscribeTopicByAlias(String topic, List<String> aliases) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pushMessageToAppByTopic(String topic, String msg, long sec, String msgType, int notifyid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unsubscribeTopicByAlias(String topic, List<String> aliases) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public int sendMsg(String clientId, String msg, long sec, String msgType, int notifyid) throws Exception{
		
		if(getVivoLimit()){
			logger.info("vivoPushLog=clientId"+clientId + ";skipContent:" + msg + ";desc:"+"当日已超出总量限制,暂停发送");
			return 0;
		}
		PushMessageDto pushMessageDto = (PushMessageDto) JSONObject.parseObject(msg, PushMessageDto.class);
		if(Tools.isNull(pushMessageDto.getContent())){
			return 0;
		}
		String authToken = getCacheredis().get(AgentRedisCacheConst.VIVO_PUSH_MESSAGE_AUTHTOKEN_PREFIX + getAppId());
		if (Tools.isNull(authToken)) {
			authToken = generateAuthToken();
		}
		Sender sender = new Sender(getAppSecret(), authToken);
		sender.initPool(20, 10);// 设置连接池参数，可选项
		
		String description = pushMessageDto.getContent();
		if(description.length()>=50){
			description =description.substring(0,50);
		}
	    
		int skipType = 3;
		String skipContent = msg;
		if(pushMessageDto.getMsgType() == PushTypeEnum.type_web_H5.getCode()){
			skipType = 2;
			skipContent = pushMessageDto.getUrl();
		}
		
		Message singleMessage = new Message.Builder()
				// .regId("630b5c8d48b05faeb5cae967872c64f4")//该测试手机设备订阅推送后生成的regId
				.alias(clientId)// 该测试手机设备订阅推送后生成的别名
				.notifyType(3) //通知类型 1:无，2:响铃，3:振动，4:响铃和振动
				.title(pushMessageDto.getTitle()) //通知标题（用于通知栏消息） 最大20个汉字（一个汉字等于两个英文字符，即最大不超过40个英文字符） 
				.content(description) //通知内容（用于通知栏消息） 最大50个汉字（一个汉字等于两个英文字符，即最大不超过100个英文字符）
				.timeToLive(60*60*24) //消息保留时长 单位：秒，取值至少60秒，最长7天。当值为空时，默认一天
				.skipType(skipType)//点击跳转类型 1：打开APP首页 2：打开链接 3：自定义 4:打开app内指定页面
				.skipContent(skipContent) //跳转内容 跳转类型为2时，跳转内容最大1000个字符，跳转类型为3或4时，跳转内容最大1024个字符，关于skipContent的内容可以参考
				.networkType(-1) //网络方式 -1：不限，1：wifi下发送，不填默认为-1
				.requestId(IdGenerater.generateId()+"")
				.build(); //用户请求唯一标识 最大64字符
		Result result = sender.sendSingle(singleMessage);
		if(result.getResult() ==0){
			logger.info("vivoPushLog_sucess=result:"+result.getResult() + ";skipContent:" + msg + ";clientId:"+clientId+";desc:" + result.getDesc());
			return 1;
		}else{
			logger.info("vivoPushLog=result:"+result.getResult() + ";skipContent:" + msg + ";clientId:"+clientId+";desc:" + result.getDesc());
			if(result.getResult() == 10307){
				UserBO userBo = getUserAgent().findByClientid(clientId);
				if(Tools.isNotNull(userBo)){
					getUserAgent().updateUserPushChannel(userBo.getUserid(), PushChannel.uninstall.getCode());
				}
			}else if(result.getResult() == 10070){
				setVivoLimit();
			}
			return 0;
		}
	}

	
	public synchronized String generateAuthToken() throws Exception{
		String authToken = null;
		Sender sender = new Sender(getAppSecret());// 实例化Sender
		sender.initPool(20, 10);// 设置连接池参数，可选项
		Result result = sender.getToken(getAppId(), getAppKey());// 发送鉴权请求
		if(result.getResult() == 0){
			authToken = result.getAuthToken();
			getCacheredis().set(AgentRedisCacheConst.VIVO_PUSH_MESSAGE_AUTHTOKEN_PREFIX + getAppId(),authToken);
			getCacheredis().expire(AgentRedisCacheConst.VIVO_PUSH_MESSAGE_AUTHTOKEN_PREFIX + getAppId(), 60*60*2);
			logger.info("vivoPushLog_sucess=result:"+result.getResult() + ";authToken:" + result.getAuthToken() + ";authToken:" + result.getDesc());
		}else{
			logger.info("vivoPushLog=result:"+result.getResult() + ";authToken:" + result.getAuthToken() + ";authToken:" + result.getDesc());
		}
		return authToken;
	}
	
	/**
	 * 设置限量
	 */
	public void setVivoLimit(){
		String key = AgentRedisCacheConst.VIVO_PUSH_MESSAGE_LIMITE_PREFIX+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_" + getAppId();
		getCacheredis().set(key,getAppId()+"");
		getCacheredis().expire(key, 60*60*24);
	}
	
	
	public boolean getVivoLimit(){
		String key = AgentRedisCacheConst.VIVO_PUSH_MESSAGE_LIMITE_PREFIX+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_" + getAppId();
		return getCacheredis().exists(key);
	}
}
