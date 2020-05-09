package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

public interface IVChatVideoService {
	
	/**
	 * 获对视频通话对方信息
	 * @return
	 * @throws Exception
	 */
	public ActionResult dialing(long userId,Long toUserId) throws Exception;

	/**
	 * 视频通话应答接口 （接听，进入，退出）
	 * @return
	 * @throws Exception
	 */
	public ActionResult communicateRes(long userId,Long orderId, Long toUserId,int status) throws Exception;
	
	/**
	 * IOS是否能进入房间
	 * @return
	 * @throws Exception
	 */
	public ActionResult isEnterRoom(Long orderId) throws Exception;
	
	/**
	 * 视频通话应答接口 （接听，进入，退出）
	 * @return
	 * @throws Exception
	 */
	public ActionResult payOrder(long userId,Long orderId) throws Exception;
	
}
