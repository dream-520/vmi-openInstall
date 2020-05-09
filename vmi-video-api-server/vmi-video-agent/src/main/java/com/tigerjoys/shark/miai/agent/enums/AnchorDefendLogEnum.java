package com.tigerjoys.shark.miai.agent.enums;

/**
 * 购买守护日志类型
 */
public enum AnchorDefendLogEnum {
	
	unknown(0, "未知类型"),
	firstPay(1, "首次直接购买本类型的守护"),
	withinPeriod(2, "有效期内购买本类型的守护"),
	withoutPeriod(3, "有效期外购买本类型的守护"),
	extend(4, "因购买其他类型守护而延长本类型守护"),
	;
	
	private int code;
	private String desc;
	
	private AnchorDefendLogEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static AnchorDefendLogEnum getByCode(int code) {
		for(AnchorDefendLogEnum uf : AnchorDefendLogEnum.values()) {
			if(uf.getCode() == code) 
				return uf;
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
