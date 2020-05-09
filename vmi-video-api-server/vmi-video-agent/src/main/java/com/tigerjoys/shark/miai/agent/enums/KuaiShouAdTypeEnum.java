package com.tigerjoys.shark.miai.agent.enums;

/**
 * 快手广告类型
 */
public enum KuaiShouAdTypeEnum {
	
	unknown(0, "未知"),
	device(1, "设备激活"),
	user(2, "用户注册类型"),
	;
	
	private int code;
	private String desc;
	
	private KuaiShouAdTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static KuaiShouAdTypeEnum getByCode(int code) {
		for(KuaiShouAdTypeEnum uf : KuaiShouAdTypeEnum.values()) {
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
