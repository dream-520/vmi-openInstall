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
@Service("iosXiaomiPush5")
public class IosXiaoMiPushServiceImpl5 extends IosXiaoMiPushServiceImpl {

	private static final String iosAccountSecret5 = PushConstant.ios_xiaomi_accountSecret5;
	
	@Override
	protected Sender initSender() {
		return new Sender(iosAccountSecret5);
	}
	
	@Override
	protected Subscription initSubscription() {
		return new Subscription(iosAccountSecret5);
	}
	
	@Override
	protected String implName() {
		return "iosXiaomiPush5";
	}
	
	
}
