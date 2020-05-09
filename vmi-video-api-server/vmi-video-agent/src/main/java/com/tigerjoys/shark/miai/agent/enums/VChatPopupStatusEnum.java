package com.tigerjoys.shark.miai.agent.enums;

/**
 * 提現状态类型
 * @author chengang
 *
 */
public enum VChatPopupStatusEnum {
	//
	call_pop(1,"呼叫弹窗"),
	short_video_open(2,"短视频显示状态"),
    ;
	
	private int code;
	private String desc;
	
	private VChatPopupStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (VChatPopupStatusEnum refer : VChatPopupStatusEnum.values()) {
			if (code == refer.getCode()) {
				return refer.getDesc();
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
