package com.tigerjoys.shark.miai.agent.service;

import com.tigerjoys.shark.miai.agent.dto.PushParamDto;

/**
 * 华为推送的网关
 * @author shiming
 *
 */
public interface IHuaWeiPushService {

	public void commonPushApp(PushParamDto param);
	
}
