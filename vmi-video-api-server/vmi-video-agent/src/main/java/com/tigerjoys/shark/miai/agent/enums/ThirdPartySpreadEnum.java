package com.tigerjoys.shark.miai.agent.enums;

/**
 * 三方广告推广枚举
 *
 */
public enum ThirdPartySpreadEnum {
	
	unknown(0 , "未知"),
	kuaishou(1 , "快手推广途径"),
	;
	
	private int code;
	private String desc;
	
	private ThirdPartySpreadEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static ThirdPartySpreadEnum getByCode(int code) {
		for(ThirdPartySpreadEnum uf : ThirdPartySpreadEnum.values()) {
			if(uf.getCode() == code) return uf;
		}
		return unknown;
	}
	
	public static String getDescByCode(int code) {
		return getByCode(code).getDesc();
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
