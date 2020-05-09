package com.tigerjoys.shark.miai.agent;

import java.util.Map;

/**
 * 用户固定验证码服务类
 * @author yangjunming
 *
 */
public interface IForbiddenRechargeAgent {

	/**
	 * 获取固定验证码
	 * @param userid
	 * @param days
	 */
	public Map<String,String> getForbiddenRechargeList()  throws Exception;
	

	
}
