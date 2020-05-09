package com.tigerjoys.shark.miai.token;

import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;

/**
 * 聊天网关的token
 * @author chengang
 *
 */

public class GateWayToken extends AbstractToken {

	/**
	 * 构造一个gateway token生成器
	 * @param redisClient - redis客户端
	 */
	public GateWayToken(CacheRedis cacheRedis) {
		super(cacheRedis, TokenConstants.VMI_GATEWAY_TOKEN_PREFIX, TokenConstants.VMI_GATEWAY_TOKEN_SECONDS);
	}

}
