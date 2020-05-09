package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.netease.SendMsgToDBObserver;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class SendMsgToDBTest extends BaseTestConfig {
	
	@Autowired
	private SendMsgToDBObserver sendMsgToDBObserver;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Test
	public void testDeal() throws Exception {
		UserBO from = userAgent.findById(140960969498099968L);
		UserBO to = userAgent.findById(140960828347187456L);
		String data = "{\"fromNick\":\"香蕉\",\"ext\":\"\",\"msgType\":\"TEXT\",\"msgidServer\":\"294809820548\",\"fromAccount\":\"139898299455963392\",\"fromClientType\":\"REST\",\"eventType\":\"1\",\"body\":\"视频通话未接听\",\"convType\":\"PERSON\",\"msgidClient\":\"f3850282-e9f4-4d03-b8f8-f5953e3336d4\",\"resendFlag\":\"0\",\"msgTimestamp\":\"1563865604007\",\"to\":\"137683067019002112\"}";
		JSONObject json = JsonHelper.toJsonObject(data);
		sendMsgToDBObserver.deal(from, to, json);
	}

}
