package com.tigerjoys.shark.miai.enums;

/**
 * 免费约会投诉类型枚举类
 * @author liuman
 */
public enum FreeAppointmentComplaintEnum {
	//
	advertisement_cheat(1,"广告欺诈"),
	obscenity(2,"淫秽色情"),
	harassment(3,"骚扰谩骂"),
	reactionary(4,"反动政治"),
	other(5,"其他内容");
	
	private int code;
	private String desc;
	
	private FreeAppointmentComplaintEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (FreeAppointmentComplaintEnum refer : FreeAppointmentComplaintEnum.values()) {
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
