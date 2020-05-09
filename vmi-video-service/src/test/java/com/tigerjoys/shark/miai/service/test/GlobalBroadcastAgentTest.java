package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.agent.IGlobalBroadcastAgent;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


public class GlobalBroadcastAgentTest extends BaseTestConfig {

	@Autowired
	private IGlobalBroadcastAgent globalBroadcastAgent;
	
	@Test
	public void testGlobalBroadcast() {
		globalBroadcastAgent.recordGlobalBroadcast(98901690555629824L);
	}
}
