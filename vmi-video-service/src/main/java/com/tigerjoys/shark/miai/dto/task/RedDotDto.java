package com.tigerjoys.shark.miai.dto.task;

import java.io.Serializable;
import java.util.List;

/**
 * 消息模块小红点数据传输类
 * 
 * @author liuman
 *
 */
public class RedDotDto implements Serializable {

	private static final long serialVersionUID = -937944862036441850L;

	/**
	 * 所有消息更新信息(聊天消息除外)
	 */
	private List<JudgeMessageUpdateDto> updateMessages;
	
	/**
	 * 消息未读数量总数
	 */
	private int unreadCount;

	public List<JudgeMessageUpdateDto> getUpdateMessages() {
		return updateMessages;
	}

	public void setUpdateMessages(List<JudgeMessageUpdateDto> updateMessages) {
		this.updateMessages = updateMessages;
	}

	public int getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}
	
}
