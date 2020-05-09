package com.tigerjoys.shark.miai.agent;

public interface IUserPayVipSendMsgAgent {

	/**
	 * 用户首次购买vip触发发送联系方式消息
	 * @param userid
	 */
	public void userFirstPayVip(long userid) throws Exception;
	
	/**
	 * 在定时任务中发送后续联系方式消息
	 * @param userid
	 */
	public void userFirstPayVipSendOther() throws Exception;
}
