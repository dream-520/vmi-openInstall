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
@Service("iosXiaomiPush3")
public class IosXiaoMiPushServiceImpl3 extends IosXiaoMiPushServiceImpl {

	private static final String iosAccountSecret3 = PushConstant.ios_xiaomi_accountSecret3;
	
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret3);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret3);
	}
	
	@Override
	protected String implName() {
		return "iosXiaomiPush3";
	}
	
	
}
