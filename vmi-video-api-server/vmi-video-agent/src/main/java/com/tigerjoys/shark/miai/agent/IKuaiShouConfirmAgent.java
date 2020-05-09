package com.tigerjoys.shark.miai.agent;

public interface IKuaiShouConfirmAgent {

	/**
	 * 添加快手广告成功引入的设备或者用户
	 * @param clientId
	 */
	public void confirm(String clientId, int type);
	
}
