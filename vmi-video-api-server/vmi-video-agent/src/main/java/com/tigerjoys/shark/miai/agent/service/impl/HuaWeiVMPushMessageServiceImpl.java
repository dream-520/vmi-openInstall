package com.tigerjoys.shark.miai.agent.service.impl;

import org.springframework.stereotype.Service;

/**
 * V密华为推送配置
 * @author shiming
 *
 */
@Service
public class HuaWeiVMPushMessageServiceImpl extends AbstractHuaWeiPushMessageService {

	@Override
	public String getAppId() {
		return "100135179";
	}

	@Override
	public String getAppSecret() {
		return "69a138d54f40685f895b254f2a800a72";
	}
	
}
