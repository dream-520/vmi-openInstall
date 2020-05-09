package com.tigerjoys.shark.miai.agent;

/**
 * 平台补助控制
 * @author shiming
 *
 */
public interface IAppUserAllowanceAgent {

	/**
	 * 是否可以分发对应的补助
	 * @param userid
	 * @return
	 */

	public int sendAllowance(String clientId, String channel, int versioncode, long userid);
	
	/**
	 * 是否弹出补助弹窗
	 * @param userid
	 * @return
	 */
	public int showAllowanceWindow(long userid);
	
	public int showAllowanceNewWindow(long userid);
	
}
