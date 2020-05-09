package com.tigerjoys.shark.miai.agent.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.service.impl.HuaWeiMYPushMessageServiceImpl;
import com.tigerjoys.shark.miai.agent.service.impl.HuaWeiVMPushMessageServiceImpl;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class HuaWeiVPushMessageTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HuaWeiVMPushMessageServiceImpl huaWeiVPushMessageServiceImpl;
	
	@Autowired
	private HuaWeiMYPushMessageServiceImpl huaWeiMYPushMessageServiceImpl;

	@Test
	public void pushMessageVMTokenTest() throws Exception {
		//{"access_token":"CF18UxRs5fl7Ckn0bj43eEj6WLLpf5XEtJzZBKeGX4e56ZhdvUJVBmUDE7BZZ6sUG04gdaGNjppo9FsAJeYdzw==","expires_in":604800}
		//{"access_token":"CF2Inapjp0INwK8Xn\/dX59s7eJMpzNfdkR13NYggTTVURrdE4KNIuavZretJQTQzZ\/q6D6+Lkt+Dl3tQBLQ\/Ww==","expires_in":604800}
		huaWeiVPushMessageServiceImpl.refreshToken();
	}
	
	@Test
	public void pushMessageVMSendTest() throws Exception {
		//{"access_token":"CF18UxRs5fl7Ckn0bj43eEj6WLLpf5XEtJzZBKeGX4e56ZhdvUJVBmUDE7BZZ6sUG04gdaGNjppo9FsAJeYdzw==","expires_in":604800}
		//String token = "AGuIKM-HvBRsYPpcEY5H1fKZYlSCVwPhmP0H-sf2f4gpy9QiuSMrrvo40BmzNan9zfqP6iwaaI_kGUUKhjM46awCTfh6LfX7GKedsQWEyvTMphiv_geHPtLYSb4W-UIUcw";
		String token = "AJEhX18YRX4HLyedRUwCxYnWv_0dzxWD2CH71G87szgYFTrZRzPMKecej5GW9_eSZJUTA4SCdqyRJUqNQia5gMhKzA5ISSEGP4rm5gFB8QT3i5OBTWdB-5yvJbuiarf4dA";
		String data = "{\"msgType\":1,\"title\":\"我充值了  来和我聊\",\"content\":\"一块来聊要\",\"appTag\":\"PushRichUserDlg\",\"user\":0,\"showNotif\":0,\"notifyId\":34,\"param\":\"{\\\"strValue1\\\":\\\"{\\\\\\\"otherUserData\\\\\\\":{\\\\\\\"userId\\\\\\\":0,\\\\\\\"nickName\\\\\\\":\\\\\\\"不天是否可以\\\\\\\",\\\\\\\"userType\\\\\\\":0,\\\\\\\"anchorStatus\\\\\\\":0,\\\\\\\"anchorStar\\\\\\\":0,\\\\\\\"anchorTypeAudio\\\\\\\":0,\\\\\\\"anchorTypeVideo\\\\\\\":0,\\\\\\\"balance\\\\\\\":\\\\\\\"余额: 20\\\\\\\"},\\\\\\\"info\\\\\\\":\\\\\\\"多聊多赚钱\\\\\\\"}\\\"}\",\"packageName\":\"com.ydwx.yoyo\",\"ios_control\":1}";
		huaWeiVPushMessageServiceImpl.sendPushMessage(token, JsonHelper.toObject(data, PushMessageDto.class));
		logger.error(JsonHelper.toJson(JsonHelper.toObject(data, PushMessageDto.class)));
	}
	
	@Test
	public void pushMessageMYTokenTest() throws Exception {
		//{"access_token":"CF2IoGfg1FYgRDRNU2fHZr+\/d0gmVdRwD9judtoe9XTX7XuV1CeuXFCxX+waHjMCoYUfZbolPMQF6H1k7yz+vQ==","expires_in":604800}
		huaWeiMYPushMessageServiceImpl.refreshToken();
	}
	
	@Test
	public void pushMessageMYSendTest() throws Exception {
		String token = "AJEhX18YRX4HLyedRUwCxYnWv_0dzxWD2CH71G87szgYFTrZRzPMKecej5GW9_eSZJUTA4SCdqyRJUqNQia5gMhKzA5ISSEGP4rm5gFB8QT3i5OBTWdB-5yvJbuiarf4dA";
		String data = "{\"msgType\":1,\"title\":\"我充值了  来和我聊\",\"content\":\"一块来聊要\",\"appTag\":\"PushRichUserDlg\",\"user\":0,\"showNotif\":0,\"notifyId\":34,\"param\":\"{\\\"strValue1\\\":\\\"{\\\\\\\"otherUserData\\\\\\\":{\\\\\\\"userId\\\\\\\":0,\\\\\\\"nickName\\\\\\\":\\\\\\\"不天是否可以\\\\\\\",\\\\\\\"userType\\\\\\\":0,\\\\\\\"anchorStatus\\\\\\\":0,\\\\\\\"anchorStar\\\\\\\":0,\\\\\\\"anchorTypeAudio\\\\\\\":0,\\\\\\\"anchorTypeVideo\\\\\\\":0,\\\\\\\"balance\\\\\\\":\\\\\\\"余额: 20\\\\\\\"},\\\\\\\"info\\\\\\\":\\\\\\\"多聊多赚钱\\\\\\\"}\\\"}\",\"packageName\":\"com.ydwx.yoyo\",\"ios_control\":1}";
		huaWeiMYPushMessageServiceImpl.sendPushMessage(token, JsonHelper.toObject(data, PushMessageDto.class));
		logger.error(JsonHelper.toJson(JsonHelper.toObject(data, PushMessageDto.class)));
	}
}
