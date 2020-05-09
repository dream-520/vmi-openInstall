package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.shark.miai.agent.ISendMessageAgent;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试推送业务
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class SendSmsTest extends BaseTestConfig {

	@Autowired
	private ISendMessageAgent sendMessageAgent;
	
	@Test
	public void testSendSMS() throws Exception {
		sendMessageAgent.sendMobileValidCode("18811315513", SendSmsTypeEnum.auth_mobile);
	}

	@Test
	public void testSendSMS2() throws Exception {
		//sendMessageAgent.sendSms(SmsParam.getSmsParam("totalCount", "45").add("handlerCount", "75"), null, SendSmsTypeEnum.paid_appoint_timer_warn, new String[]{"18811315513"});
	}

}
