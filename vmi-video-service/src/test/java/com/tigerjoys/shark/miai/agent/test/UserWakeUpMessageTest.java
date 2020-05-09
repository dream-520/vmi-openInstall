package com.tigerjoys.shark.miai.agent.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.agent.IUserWakeUpMessageAgent;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class UserWakeUpMessageTest extends BaseTestConfig {

	@Autowired
	private IUserWakeUpMessageAgent userWakeUpMessageAgent;
	
	@Test
	public void userWakeUpMessageTest() throws Exception {
		//测试用户登录
		//long userid = 136256810467262720L;
		//测试主播登录
		//long userid = 67244811045896448L;
		long userid = 138827641168920832L;
		userWakeUpMessageAgent.wakeUpMessage(userid);
		Thread.sleep(5000);
	}
}
