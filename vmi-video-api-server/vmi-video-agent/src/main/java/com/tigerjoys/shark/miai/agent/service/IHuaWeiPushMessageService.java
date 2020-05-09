package com.tigerjoys.shark.miai.agent.service;

import java.io.IOException;

import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;

public interface IHuaWeiPushMessageService {

	public void sendPushMessage(String token, PushMessageDto msg) throws IOException;
	
	public String getAppId();
	
	public String getAppSecret();
	
	public String getAccessToken(String appSecret, String appid);
}
