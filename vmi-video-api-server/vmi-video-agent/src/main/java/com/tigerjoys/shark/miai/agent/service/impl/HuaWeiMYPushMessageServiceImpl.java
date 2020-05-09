package com.tigerjoys.shark.miai.agent.service.impl;

import org.springframework.stereotype.Service;

/**
 * 密约华为推送配置
 * @author shiming
 *
 */
@Service
public class HuaWeiMYPushMessageServiceImpl extends AbstractHuaWeiPushMessageService {

	@Override
	public String getAppId() {
		return "100441513";
	}

	@Override
	public String getAppSecret() {
		return "091afe265815101e0ca648aa6ddd241f";
	}

}
