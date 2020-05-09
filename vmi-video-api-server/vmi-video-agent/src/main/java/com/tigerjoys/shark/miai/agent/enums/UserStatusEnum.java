package com.tigerjoys.shark.miai.agent.enums;

/**
 * 用户状态
 * @author 刘满
 *
 */
public enum UserStatusEnum {
	
	disable(0,"停用"),
	normal(1 , "正常"),
	hidden(2 , "隐藏"),
    ;
	
	private final int code;
	private final String desc;
	
	private UserStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (UserStatusEnum refer : UserStatusEnum.values())
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
