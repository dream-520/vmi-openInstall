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
@Service("iosXiaomiPush7")
public class IosXiaoMiPushServiceImpl7 extends IosXiaoMiPushServiceImpl {

	private static final String iosAccountSecret7 = PushConstant.ios_xiaomi_accountSecret7;
	
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret7);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret7);
	}
	
	@Override
	protected String implName() {
		return "iosXiaomiPush7";
	}
	
	
}
