package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户奖励枚举类
 * 
 * @author liuman
 *
 */
public enum UserRewardEnum {

	gold(1 , "金币奖励"),
	rmb(2, "人民币奖励"),
	prop(3, "道具奖励"),
	;

	/**
	 * 枚举编号
	 */
	private int code;
	
	/**
	 * 枚举名称
	 */
	private String name;

	private static final Map<Integer, UserRewardEnum> all_desc = new HashMap<Integer, UserRewardEnum>();

	static {
		for (UserRewardEnum refer : UserRewardEnum.values()) {
			all_desc.put(refer.getCode(), refer);
		}
	}

	private UserRewardEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(int code) {
		UserRewardEnum e = all_desc.get(code);
		if(e != null) return e.getName();
		return null;
	}
	
	public static UserRewardEnum getEnumByCode(int code) {
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
