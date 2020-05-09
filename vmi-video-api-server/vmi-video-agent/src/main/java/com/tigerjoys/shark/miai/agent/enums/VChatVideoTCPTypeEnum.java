package com.tigerjoys.shark.miai.agent.enums;

/**
 * 提現状态类型
 * @author chengang
 *
 */
public enum VChatVideoTCPTypeEnum {

	video(1,"视频"),
	popup(2,"弹窗"),
    ;
	
	private int code;
	private String desc;
	
	private VChatVideoTCPTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (VChatVideoTCPTypeEnum refer : VChatVideoTCPTypeEnum.values()) {
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
