package com.tigerjoys.shark.miai.agent;

/**
 * 付费代理人
 * @author yangjunming
 *
 */
public interface IVipAgent {
	/**
	 * 增送VIP月数
	 * @param userid
	 * @param month 赠送月
	 * @param orderId
	 * @throws Exception
	 */
	public int giveAway(long userid, int month, String orderId) throws Exception;
	
	/**
	 * 赠送VIP天数
	 * @param userid
	 * @param day
	 * @param orderId
	 * @return
	 */
	public int giveAwayDay(long userid, int day, String orderId) throws Exception;
}
