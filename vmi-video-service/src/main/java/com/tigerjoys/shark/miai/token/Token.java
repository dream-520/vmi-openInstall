package com.tigerjoys.shark.miai.token;

import com.tigerjoys.nbs.common.exception.NotKeyExistsException;

/**
 * Token接口
 * @author chengang
 *
 */
public interface Token {
	
	/**
	 * 创建一个新的token
	 * @param id - String
	 * @return String
	 */
	String createToken(String id);
	
	/**
	 * 将token并且放入到redis中
	 * @param id - String
	 * @param token - String
	 * @return String
	 */
	void addTokenToRedis(String id , String token);
	
	/**
	 * 创建一个新的token并且放入到redis中
	 * @param id - String
	 * @return String
	 */
	String createAndAddTokenToRedis(String id);
	
	/**
	 * 根据id获取token
	 * @param id - String
	 * @return String
	 */
	String getToken(String id);
	
	/**
	 * 根据token获取ID
	 * @param token - String
	 * @return String
	 */
	String getId(String token);
	
	/**
	 * 刷新指定ID的token
	 * @param id - String
	 * @throws NotKeyExistsException
	 */
	void refreshToken(String id) throws NotKeyExistsException;
	
	/**
	 * 验证指定ID的token
	 * @param id - String
	 * @param token - String
	 * @return boolean
	 */
	boolean validToken(String id , String token);
	
	/**
	 * 删除指定ID的token
	 * @param id - String
	 */
	void removeToken(String id);
	
}
