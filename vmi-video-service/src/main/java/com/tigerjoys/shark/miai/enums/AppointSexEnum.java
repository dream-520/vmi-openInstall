package com.tigerjoys.shark.miai.enums;

/**
 * 约会性别
 * @author yangjunming
 *
 */
public enum AppointSexEnum {
	//
	unlimited(0,"不限"),
	men(1, "仅限男士"),
	women(2 , "仅限女士"),
    ;
	
	private int code;
	private String desc;
	
	private AppointSexEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (AppointSexEnum refer : AppointSexEnum.values()) {
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
