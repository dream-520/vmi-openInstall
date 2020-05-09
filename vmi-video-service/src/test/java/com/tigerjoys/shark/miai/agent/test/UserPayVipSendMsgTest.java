package com.tigerjoys.shark.miai.agent.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.agent.IUserPayVipSendMsgAgent;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试首次购买vip触发的发送联系方式的业务
 * @author shiming
 *
 */
public class UserPayVipSendMsgTest extends BaseTestConfig {

	@Autowired
	private IUserPayVipSendMsgAgent userPayVipSendMsgAgent;
	
	@Test
	public void testUserFirstPayVip() throws Exception {
		userPayVipSendMsgAgent.userFirstPayVip(32392036843847936L);
	}
	
	//@Test
	public void testUserFirstPayVipSendOther() throws Exception {
		userPayVipSendMsgAgent.userFirstPayVipSendOther();
	}
}
