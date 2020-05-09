package com.tigerjoys.shark.miai.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.agent.service.INewPushService;

/**
 * 向ios端推送消息服务类
 * @author liuman  
 *
 */
@Service
public class IosPushMessageService2 extends AbstractPushMessageService{
	
	@Autowired
	@Qualifier("iosXiaomiPush2")
	private INewPushService iosXiaomiPush;
	
	@Override
	protected INewPushService getNewPushService() {
		return iosXiaomiPush;
	}
	
}
