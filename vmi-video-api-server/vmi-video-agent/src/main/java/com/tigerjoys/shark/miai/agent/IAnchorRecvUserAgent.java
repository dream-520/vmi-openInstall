package com.tigerjoys.shark.miai.agent;

import java.util.List;

public interface IAnchorRecvUserAgent {

	/**
	 * 检测对应的用户是否已经在视频过 聊过的 发过礼物的库中
	 * @param userid
	 * @param otherid
	 * @param type
	 */
	public void checkAnchorRecvUser(long userid, long otherid, int type);
	
	/**
	 * 检测用户是否对给定的主播设置了隐身功能
	 * @param userid
	 * @param anchorid
	 * @return
	 */
	public boolean checkInvisibility(long userid, long anchorid);
	
	/**
	 * 获取对本主播设置了隐身的用户集合
	 * @param userid
	 * @param anchorid
	 * @return
	 */
	public List<Long> userInvisibilityAnchor(long anchorid);
}
