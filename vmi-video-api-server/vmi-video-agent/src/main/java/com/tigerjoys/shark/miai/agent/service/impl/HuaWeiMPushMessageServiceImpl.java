package com.tigerjoys.shark.miai.agent.service.impl;

import org.springframework.stereotype.Service;

/**
 * 陌约华为推送配置
 * @author shiming
 *
 */
@Service
public class HuaWeiMPushMessageServiceImpl extends AbstractHuaWeiPushMessageService {

	@Override
	public String getAppId() {
		return "100310037";
	}

	@Override
	public String getAppSecret() {
		return "5f50fe050245600f1fbb489f305d74e1";
	}
	
}
