package com.tigerjoys.shark.miai.agent.test;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 用户在线列表测试类
 * @author chengang
 *
 */
public class UserOnlineAgentTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;
	
	@Autowired
	private IUserOnlineStateAgent userOnlineStateAgent;
	
	/**
	 * 测试用户在线
	 * @throws Exception
	 */
	@Test
	public void existsOnlineTest() throws Exception{
		long userid = 10022568L;
		String clientId = "asd32deffew22ssaaw2";
		
		userOnlineListAgent.addOnlineUser(userid, clientId);
		
		boolean b = userOnlineListAgent.existsOnline(userid);
		logger.info("用户是否在线：{}" , b);
		
		Assert.assertTrue(b);
	}
	
	@Test
	public void getOnlineUserClientIdTest() throws Exception {
		long userid = 10022568L;
		
		String clientId = userOnlineListAgent.getOnlineUserClientId(userid);
		
		logger.info("clientId : {}" , clientId);
		
		Assert.assertEquals(clientId, "asd32deffew22ssaaw2");
	}
	
	@Test
	public void refreshOnlineUserTest() throws Exception {
		long userid = 10022568L;
		String clientId = "asd32deffew22ssaaw2";
		
		userOnlineListAgent.refreshOnlineUser(userid, clientId);
		
		boolean b = userOnlineListAgent.existsOnline(userid);
		logger.info("用户是否在线：{}" , b);
		
		Assert.assertTrue(b);
	}
	
	@Test
	public void removeOnlineUserTest() throws Exception {
		long userid = 10022568L;
		
		userOnlineListAgent.removeOnlineUser(userid);
		
		boolean b = userOnlineListAgent.existsOnline(userid);
		logger.info("用户是否在线：{}" , b);
		
		Assert.assertFalse(b);
	}
	
	@Test
	public void removeExpireUserOnlineListTest() throws Exception {
		long c = userOnlineListAgent.removeExpireUserOnlineList();
		logger.info("清空在线列表数量 = {}" , c);
	}
	
	@Test
	public void saveUserOnlineTimeLogTest() throws Exception {
		userOnlineListAgent.saveUserOnlineTimeLog();
	}

	@Test
	public void onlineStateTest() throws Exception{
		int state = userOnlineStateAgent.userOnlineState(36775801156337920L);
		System.err.println(state);
	}
}
