package com.tigerjoys.shark.miai.agent.dto;

/**
 * 视频聊天TCP通知
 * @author yangjunming
 *
 */
public class VChatVideoTCPDto {
	/**
	 * 类型  1视频
	 */
	private int type;
	/**
	 * 子类型   1  来电  2  进房间  3 退出房间
	 */
	private int subType;
	
	private Object data;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
