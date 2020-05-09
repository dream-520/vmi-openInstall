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
@Service("iosXiaomiPush6")
public class IosXiaoMiPushServiceImpl6 extends IosXiaoMiPushServiceImpl {

	private static final String iosAccountSecret6 = PushConstant.ios_xiaomi_accountSecret6;
	
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret6);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret6);
	}
	
	@Override
	protected String implName() {
		return "iosXiaomiPush6";
	}
	
	
}
