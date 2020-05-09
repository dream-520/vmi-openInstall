package com.tigerjoys.shark.miai.enums;

/**
 * 达人申请结果枚举
 * @author chengang
 *
 */
public enum TalentButtonTypeEnum {
	//
	TALENT(1,"是达人"),
	WAIT_AUDIT(2,"正在审核中"),
	NO_TALENT(3, "不是达人"),
    ;
	
	private final int code;
	private final String desc;
	
	private TalentButtonTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (TalentButtonTypeEnum refer : TalentButtonTypeEnum.values()) {
			if (code == refer.getCode()) {
				return refer.getDesc();
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
