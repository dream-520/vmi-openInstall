package com.tigerjoys.shark.miai.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.enums.VivoPushMessageEnum;

@Service(VivoPushMessageEnum.com_ydwx_yoyo3)
public class VivoPushMessageServiceImpl2 extends AbstractVivoPushService {

	/**
	 * 读取classpath下properties文件的信息单例类
	 */
	private static ServiceConfig serviceConfig = ServiceConfig.getInstance();
	
	private final int impl = VivoPushMessageEnum.vivo_com_ydwx_yoyo3.getImpl();

	private final int android_a_vivo_appId = serviceConfig.getInt("android_vivo_appId_"+impl , 0);
	private final String android_a_vivo_appKey = serviceConfig.getString("android_vivo_appKey_"+impl , "");
	private final String android_a_vivo_appSecret = serviceConfig.getString("android_vivo_appSecret_"+impl , "");
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUSH_MESSAGE_CACHE)
	private CacheRedis pushCacheRedis;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Override
	public String getAppSecret() {
		return android_a_vivo_appSecret;
	}

	@Override
	public int getAppId() {
		return android_a_vivo_appId;
	}

	@Override
	public String getAppKey() {
		return android_a_vivo_appKey;
	}

	@Override
	public CacheRedis getCacheredis() {
		return pushCacheRedis;
	}

	@Override
	public IUserAgent getUserAgent() {
		return userAgent;
	}

	
}
