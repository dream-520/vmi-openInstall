package com.tigerjoys.shark.miai.enums;

/**
 * 提現状态类型
 * @author chengang
 *
 */
public enum ExtractStatusEnum {
	//
	reviewing(0,"审核中"),
	settle(1, "已结算"),
	refuse(2 , "已拒绝"),
    ;
	
	private int code;
	private String desc;
	
	private ExtractStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (ExtractStatusEnum refer : ExtractStatusEnum.values()) {
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
