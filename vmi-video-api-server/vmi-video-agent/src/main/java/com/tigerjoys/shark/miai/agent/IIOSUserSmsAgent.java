package com.tigerjoys.shark.miai.agent;

import java.util.Map;

/**
 * 用户固定验证码服务类
 * @author yangjunming
 *
 */
public interface IIOSUserSmsAgent {

	/**
	 * 获取固定验证码
	 * @param userid
	 * @param days
	 */
	public Map<String,String> getUserSmsList()  throws Exception;
	
	/**
	 * 获取提审账户的用户ID
	 * @param userid
	 * @param days
	 */
	public Map<Long,String> getUserIdList()  throws Exception;
	
}
