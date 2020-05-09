package com.tigerjoys.shark.miai.agent.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * Tcp消息发送
 * @author yangjunming
 *
 */
public interface IVchatTcpMessageService {

	
	
	/**
	 * 监黄通知
	 * @param userId
	 * @param type  0  不关闭通话  1 关闭通话
	 * @param hitInfo  提示信息
	 * @return
	 * @throws Exception
	 */
	public ActionResult checkPorn(long userid,long orderId, int type, String hitInfo) throws Exception ;
	
	
	/**
	 * 发送短视频按钮显示开关消息    
	 * @param userid
	 * @param orderId
	 * @param status    0  不显示  1显示
	 * @return
	 * @throws Exception
	 */
	public ActionResult sendShortVideoOpenStatus(long userid, int status) throws Exception;
	
	
}
