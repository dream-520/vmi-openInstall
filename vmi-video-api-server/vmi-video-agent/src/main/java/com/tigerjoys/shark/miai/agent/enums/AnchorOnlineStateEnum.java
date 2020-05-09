package com.tigerjoys.shark.miai.agent.enums;

/**
 * 主播在线状态枚举
 * @author shiming
 */
public enum AnchorOnlineStateEnum {
	
	offline(0,"离线"),
	quiet(1,"勿扰"),
	talking(2, "在聊"),
	online(3, "在线"),
    ;
	
	private int code;
	private String desc;
	
	private AnchorOnlineStateEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (AnchorOnlineStateEnum refer : AnchorOnlineStateEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
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
