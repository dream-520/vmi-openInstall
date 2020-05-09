package com.tigerjoys.shark.miai.enums;

/**
 * 达人申请结果枚举
 * @author chengang
 *
 */
public enum TalentApplyResultEnum {
	//
	TALENT(1,"是达人"),
	NO_VIP(10,"必须是达人VIP才能成为达人"),
	NO_PERFECT_PROFILE(11, "未完善个人信息"),
	NO_PERFECT_CONCAT(12 , "未完善联系方式"),
	NO_AUTH_VIDEO(13 , "未通过视频认证"),
	NO_AUTH_APPOINT(30 , "未通过付费约信息审核"),
	NO_FILLIN_APPOINT(31 , "未填写过付费约信息"),
	NO_PAY_APPOINT(32 , "未缴纳保证金"),
	CURR_AUTH_APPOINT(50 , "付费约信息正在审核中"),
	TALENT_STATUS_ERROR(90 , "付费约信息已被下架"),
    ;
	
	private final int code;
	private final String desc;
	
	private TalentApplyResultEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (TalentApplyResultEnum refer : TalentApplyResultEnum.values()) {
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
