package com.tigerjoys.shark.miai.agent.enums;

/**
 * 用于区分对应的进入技能详情中获取数据的请求来源
 *   根据请求来源不同拼接不同的数据
 * @author shiming
 *
 */
public enum RequestPaidSkillDetailSourceEnum {
	
	homeInDetail(1, "首页进入技能页获取技能详情"),
	DetailInOrder(2 , "技能详情进下单获取技能详情"),
	ManagerToDetail(3 , "通过技能管理页进入技能详情");
	
	private final int code;
	private final String desc;
	
	private RequestPaidSkillDetailSourceEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (RequestPaidSkillDetailSourceEnum refer : RequestPaidSkillDetailSourceEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
