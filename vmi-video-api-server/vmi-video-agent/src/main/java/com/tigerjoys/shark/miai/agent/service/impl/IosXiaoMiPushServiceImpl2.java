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
@Service("iosXiaomiPush2")
public class IosXiaoMiPushServiceImpl2 extends IosXiaoMiPushServiceImpl {

	private static final String iosAccountSecret2 = PushConstant.ios_xiaomi_accountSecret2;
	
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret2);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret2);
	}
	
	@Override
	protected String implName() {
		return "iosXiaomiPush2";
	}
	
	
}
