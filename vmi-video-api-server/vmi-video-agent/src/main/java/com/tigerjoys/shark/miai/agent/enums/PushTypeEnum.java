package com.tigerjoys.shark.miai.agent.enums;

/**
 * 蜜爱轻社交推送类型枚举
 * @author liuman
 *
 */
public enum PushTypeEnum {
	
	default_type(0,"不跳转"),
	type_goto_app(1,"跳转app界面"),
	type_goto_H5(2,"跳转H5界面"),
	type_web_H5(3,"使用外部游览器打开H5界面"),
	;
	
	private int code;
	private String desc;

	private PushTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PushTypeEnum refer : PushTypeEnum.values())
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
