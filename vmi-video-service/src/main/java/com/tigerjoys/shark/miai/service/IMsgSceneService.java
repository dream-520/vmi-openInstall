package com.tigerjoys.shark.miai.service;

public interface IMsgSceneService {

	/**
	 * app启动触发场景消息
	 * @param userid
	 */
	public void defineMsgScene(long userid);
	
	/**
	 * 用户回应检测是否进行场景
	 * @param userid
	 * @param otherid
	 */
	public void sendMsgScene(long userid, long otherid);
	
	/**
	 * 检测用户给假主播发送消息触发场景
	 * @param userid
	 */
	public void checkAutoInMsgScene(long userid, long anchorid);
	
	/**
	 * 触发新的场景消息机制
	 * @param userid
	 * @param otherid
	 */
	public void sendNewMsgScene(long userid, long otherid);
}
