package com.tigerjoys.shark.miai.agent.enums;

/**
 * 推送消息内容的枚举
 * @author liuman
 *
 */
public enum PushMessageEnum {
	
	has_attend_user(0,"亲，我关注了你，你也赶紧关注我吧"),
    ;
	
	private int code;
	private String desc;
	
	private PushMessageEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PushMessageEnum refer : PushMessageEnum.values())
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
