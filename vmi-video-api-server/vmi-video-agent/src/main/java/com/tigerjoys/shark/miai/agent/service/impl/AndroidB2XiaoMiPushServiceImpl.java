package com.tigerjoys.shark.miai.agent.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.shark.miai.agent.constant.PushConstant;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.Subscription;

/**
 * android B2端小米推送服务类
 * 
 * @author liuman
 *
 */
@Service("androidB2XiaomiPush")
public class AndroidB2XiaoMiPushServiceImpl extends AbstractMiPushService{

	private static final Log logger = LogFactory.getLog(AndroidB2XiaoMiPushServiceImpl.class);

	private static final String androidAccountSecret = PushConstant.android_B2_xiaomi_accountSecret;
	private static final String androidAccountPackageName = PushConstant.android_B2_xiaomi_accountPackageName;
	
	@Override
	protected void useMiPush() {
		Constants.useOfficial();
	}
	
	@Override
	protected Sender initSender() {
		return new Sender(androidAccountSecret);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(androidAccountSecret);
	}
	
	@Override
	protected Message buildMessage(String msg, long sec, String msgType, int notifyid) {
		PushMessageDto pushMessageDto = (PushMessageDto) JSONObject.parseObject(msg, PushMessageDto.class);
		logger.info("androidB2XiaomiPush notifyid=" + notifyid);
		String messagePayload = msg;
		String title = pushMessageDto.getTitle();
		String description = pushMessageDto.getContent();
		Message message = null;
		//检测是否是外部h5
		if(pushMessageDto.getMsgType() == PushTypeEnum.type_web_H5.getCode()) {
			message = new Message.Builder()
					.title(title)
					.description(description)
					.payload(messagePayload)
					//.restrictedPackageName(androidAccountPackageName)
					.restrictedPackageName(androidAccountPackageName)
					.passThrough(0) // 1表示透传消息，0表示通知栏消息
					.notifyType(1) // 使用默认提示音提示
					.extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_WEB)
		            .extra(Constants.EXTRA_PARAM_WEB_URI, pushMessageDto.getUrl())
					.timeToLive(sec).build();
		} else {
			message = new Message.Builder()
					.title(title)
					.description(description)
					.payload(messagePayload)
					//.restrictedPackageName(androidAccountPackageName)
					.restrictedPackageName(androidAccountPackageName)
					.passThrough(Integer.parseInt(msgType)) // 1表示透传消息，0表示通知栏消息
					.notifyType(1) // 使用默认提示音提示
					.notifyId(notifyid).timeToLive(sec).build();
		}
		return message;
	}

	@Override
	protected String implName() {
		return "androidB2XiaomiPush";
	}
	
	
}
