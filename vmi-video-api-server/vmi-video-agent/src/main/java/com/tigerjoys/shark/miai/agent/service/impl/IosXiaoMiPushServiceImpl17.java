package com.tigerjoys.shark.miai.agent.service.impl;

import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.agent.constant.PushConstant;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.Subscription;

/**
 * ios端小米推送服务类
 * 
 * @author liuman
 *
 */
@Service("iosXiaomiPush17")
public class IosXiaoMiPushServiceImpl17 extends IosXiaoMiPushServiceImpl {

	private static final String iosAccountSecret17 = PushConstant.ios_xiaomi_accountSecret17;
	
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret17);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret17);
	}
	
	@Override
	protected String implName() {
		return "iosXiaomiPush17";
	}
	
	
}
