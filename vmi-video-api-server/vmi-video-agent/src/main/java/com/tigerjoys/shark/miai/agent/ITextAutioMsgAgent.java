package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.enums.TextAudioMsgTypeEnum;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;

/**
 * 文本聊天接口
 * @author yangjunming
 *
 */
public interface ITextAutioMsgAgent {

	/**
	 * 文本聊天前检查，看是否可以文本聊天
	 * @param userId    调用方
	 * @param toUserId  被叫方ID
	 * @return
	 * @throws Exception
	 */
	public int addAutioMsg(UserAudioChatInfoLogEntity audio) throws Exception;
	
	
	/**
	 * 更新
	 * @param audio
	 * @return
	 * @throws Exception
	 */
	public int updateAutioMsg(UserAudioChatInfoLogEntity audio) throws Exception;
	
	
	public int addPictureMsg(UserAudioChatInfoLogEntity log) throws Exception;
	
}
