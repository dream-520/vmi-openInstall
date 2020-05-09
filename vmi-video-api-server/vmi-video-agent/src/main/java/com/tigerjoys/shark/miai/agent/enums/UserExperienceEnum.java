package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 经验值枚举类
 * 
 * @author chengang
 *
 */
public enum UserExperienceEnum {

	perfect_profile(1 , "完善资料"),
	daily_active(2, "每日活跃"),
	pilfer_redpackage(3, "偷红包"),
	recharge(4 , "每次充值"),
	share(5 , "分享到第三方平台"),
	publish_wish(6 , "每天发心愿"),
	fans(7 , "粉丝数量"),
	;

	/**
	 * 枚举编号
	 */
	private int code;
	
	/**
	 * 枚举名称
	 */
	private String name;

	private static final Map<Integer, UserExperienceEnum> all_desc = new HashMap<Integer, UserExperienceEnum>();

	static {
		for (UserExperienceEnum refer : UserExperienceEnum.values()) {
			all_desc.put(refer.getCode(), refer);
		}
	}

	private UserExperienceEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(int code) {
		UserExperienceEnum e = all_desc.get(code);
		if(e != null) return e.getName();
		return null;
	}
	
	public static UserExperienceEnum getEnumByCode(int code) {
		return all_desc.get(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
