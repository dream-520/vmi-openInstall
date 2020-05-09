package com.tigerjoys.shark.miai.agent.enums;

public enum FirstPayTypeEnum {
	all(0 , "全部类型"),
	diamond(1 , "充钻石"),
	flower(2 , "充小红花"),
	;
	
	private int code;
	private String desc;
	
	private FirstPayTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static FirstPayTypeEnum getByCode(int code) {
		for(FirstPayTypeEnum uf : FirstPayTypeEnum.values()) {
			if(uf.getCode() == code) return uf;
		}
		return all;
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
