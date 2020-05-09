package com.tigerjoys.shark.miai.token;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.BootstrapListener;

/**
 * token服务
 * 
 * @author chengang
 */

public final class TokenService {
	
	private static TokenService tokenService = new TokenService();
	
	
	private static GateWayToken gateWayToken = null;
	

	private TokenService(){
		
	}
	
	public static TokenService instance() {
		if (gateWayToken == null) {
			CacheRedis cacheRedis = BootstrapListener.getSpringContext().getBean("publicRedisCache", CacheRedis.class);
			gateWayToken = new GateWayToken(cacheRedis);
		}
		return tokenService;
	}

	/**
	 * 生成用户token
	 * 
	 * @param clientId
	 * @return
	 */
	public  String createToken(String clientId) {
		return gateWayToken.createToken(clientId);
	}

	/**
	 * 将token放入到redis中
	 * 
	 * @param clientId
	 * @param token
	 * @return String
	 */
	public  void addTokenToRedis(String clientId, String token) {
		gateWayToken.addTokenToRedis(clientId, token);
	}

	/**
	 * 创建token并放入到redis中
	 * 
	 * @param clientId
	 * @return
	 */
	public  String createAndAddTokenToRedis(String clientId) {
		String token = createToken(clientId);
		addTokenToRedis(clientId, token);
		return token;
	}

	/**
	 * 获取token
	 * 
	 * @param clientId
	 * @return
	 */
	public  String getToken(String clientId) {
		return gateWayToken.getToken(clientId);
	}

	/**
	 * 获取clientId
	 * 
	 * @param token
	 * @return
	 */
	public  String getClientId(String token) {
		return gateWayToken.getId(token);
	}

	/**
	 * 验证用户token
	 * 
	 * @param clientId
	 * @param token
	 * @return boolean
	 */
	public  boolean validToken(String clientId, String token) {
		return gateWayToken.validToken(clientId, token);
	}

	/**
	 * 移除token
	 * 
	 * @param clientId
	 */
	public  void removeToken(String clientId) {
		gateWayToken.removeToken(clientId);
	}

}
