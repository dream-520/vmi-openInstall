package com.tigerjoys.shark.miai.agent;

public interface IUserOnlineStateAgent {

	/**
	 * 获取用户或者主播的在线状态的方法
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int userOnlineState(long userid) throws Exception;
	
	/**
	 * 用于专门处理用户在线状态的方法
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int onlineState(long userid) throws Exception;
}
