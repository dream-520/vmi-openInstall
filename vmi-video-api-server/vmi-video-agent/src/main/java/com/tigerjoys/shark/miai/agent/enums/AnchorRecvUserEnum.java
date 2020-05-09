package com.tigerjoys.shark.miai.agent.enums;

public enum AnchorRecvUserEnum {

	video(1, "视频过"),
	msg(2, "发过消息"),
	gift(3, "发过礼物"),
    ;
	
	private int code;
	private String desc;
	
	private AnchorRecvUserEnum(int code, String desc) {
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
