package com.tigerjoys.shark.miai.agent.service.impl;

import org.shark.miai.common.constant.CommonConst;
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
 * ios端小米推送服务类
 * 
 * @author liuman
 *
 */
@Service("iosXiaomiPush")
public class IosXiaoMiPushServiceImpl extends AbstractMiPushService {

	private static final String iosAccountSecret = PushConstant.ios_xiaomi_accountSecret;
	
	@Override
	protected void useMiPush() {
		if(CommonConst.IS_TEST) {
			Constants.useSandbox();
		} else {
			Constants.useOfficial();
		}
	}
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret);
	}
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret);
	}
	@Override
	protected Message buildMessage(String msg, long sec, String msgType, int notifyid) {
		PushMessageDto pushMessageDto = (PushMessageDto) JSONObject.parseObject(msg, PushMessageDto.class);
	    Message message = null;
		//检测是否是外部h5
		if(pushMessageDto.getMsgType() == PushTypeEnum.type_web_H5.getCode()) {
			message = new Message.IOSBuilder()
		            .description(pushMessageDto.getTitle() + "\n" + pushMessageDto.getContent())
		            .soundURL("default")    // 消息铃声
		            .badge(1)               // 数字角标
		            .category("action")     // 快速回复类别
//		            .extra("key", "value")  // 自定义键值对
		            .extra("payload", msg)
		            .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_WEB)
		            .extra(Constants.EXTRA_PARAM_WEB_URI, pushMessageDto.getUrl())
		            .build();
		} else {
			message = new Message.IOSBuilder()
		            .description(pushMessageDto.getTitle() + "\n" + pushMessageDto.getContent())
		            .soundURL("default")    // 消息铃声
		            .badge(1)               // 数字角标
		            .category("action")     // 快速回复类别
//		            .extra("key", "value")  // 自定义键值对
		            .extra("payload", msg)
		            .build();
		}
	    return message;
	}
	@Override
	protected String implName() {
		return "iosXiaomiPush";
	}
	
	
}
