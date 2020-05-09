package com.tigerjoys.shark.miai.enums;

/**
 * 提現状态类型
 * @author chengang
 *
 */
public enum VChatVideoTCPVideoTypeEnum {
	//
	answer(1,"来电"),
	enter_room(2, "进入房间"),
	exit(3 , "退出"),
    ;
	
	private int code;
	private String desc;
	
	private VChatVideoTCPVideoTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (VChatVideoTCPVideoTypeEnum refer : VChatVideoTCPVideoTypeEnum.values()) {
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
