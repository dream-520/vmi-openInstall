package com.tigerjoys.shark.miai.agent;

/**
 * 用户vip功能
 * @author shiming
 *
 */
public interface IUserVipAgent {

	/**
	 * 增加用户vip对应的天数
	 * @param userid
	 * @param days
	 */
	public void increaseVipDay(long userid, int days);
}
