package com.tigerjoys.shark.miai.service;

import javax.servlet.http.HttpServletRequest;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.dto.UserBO;

/**
 * 文本聊天接口
 * @author yangjunming
 *
 */
public interface IVChatTextYXService {

	/**
	 * 文本聊天前检查，看是否可以文本聊天
	 * @param userId    调用方
	 * @param toUserId  被叫方ID
	 * @return
	 * @throws Exception
	 */
	public ActionResult textChatCheck(long userId, Long toUserId) throws Exception;
	
	
	
	/**
	 * 支付聊天信息
	 * @param userId    调用方
	 * @param toUserId  被叫方ID
	 * @return
	 * @throws Exception
	 */
	public ActionResult payChat(long userId, Long toUserId,String chatText) throws Exception;
	
	
	/**
	 * 语音图片发送前检查
	 * @param userId    调用方
	 * @param toUserId  被叫方ID
	 * @param type  0 语音  1 图片
	 * @return
	 * @throws Exception
	 */
	public ActionResult checkAudioSend(long userId, long otherId,int type) throws Exception;
	
	/**
	 * 查看语音消息
	 * @param userId    调用方ID
	 * @param toUserId  语音ID
	 * @return
	 * @throws Exception
	 */
	public ActionResult getAudioMsg(long userId, long audioId) throws Exception;
	
	
	/**
	 * 插入消息历史
	 * @param userId
	 * @param toUserId
	 * @param chatText
	 * @throws Exception
	 */
	public void insertAutoChatTextHistory(long userId,long toUserId,String chatText) throws Exception;
	
	/**
	 * 语音
	 * @param request
	 * @throws Exception
	 */
	public void audioCallBackRecv(long orderId ,HttpServletRequest request) throws Exception;
	
	/**
	 * 核查是否显示 VIP弹窗
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean checkShowVIPFragment(long userId) throws Exception;
	
	/**
	 * 核查是否显示 VIP弹窗
	 * @param userBO
	 * @return
	 * @throws Exception
	 */
	public boolean checkShowVIPFragment(UserBO userBO) throws Exception;
	
	/**
	 * VIP弹窗设置
	 * @return
	 * @throws Exception
	 */
	public ActionResult CheckFailVIPReturnDesc() throws Exception ;
	
}
