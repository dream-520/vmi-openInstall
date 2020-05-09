package com.tigerjoys.shark.miai.enums;

/**
 * VIP支付类型
 * @author yangjunming
 *
 */
public enum VIPPayTypeEnum {
	//
	diamond(1,"钻石"),
	energy(2, "能量"),
    ;
	
	private int code;
	private String desc;
	
	private VIPPayTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (VIPPayTypeEnum refer : VIPPayTypeEnum.values()) {
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
