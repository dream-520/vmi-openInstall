package com.tigerjoys.shark.miai.agent.enums;

/**
 * 是否需要调用方自己填充content类型枚举
 * @author liuman
 *
 */
public enum PushContentTypeEnum {
	
	necessary(0,"需要"),
	unnecessary(1,"不需要"),
	;
	
	private int code;
	private String desc;

	private PushContentTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PushContentTypeEnum refer : PushContentTypeEnum.values())
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
