package com.tigerjoys.shark.miai.agent;

import java.util.Map;
import java.util.Set;

/**
 * 用户固定验证码服务类
 * @author shiming
 *
 */
public interface IUserSpecialRechargeAgent {


	/**
	 * 获取提审账户的用户ID
	 * @param userid
	 * @param days
	 */
	public Set<Long> getUserIdList() throws Exception;
	
}
