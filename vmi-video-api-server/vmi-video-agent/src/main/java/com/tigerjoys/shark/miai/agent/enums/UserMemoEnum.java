package com.tigerjoys.shark.miai.agent.enums;

/**
 * 用户备注枚举类
 * @author chengang
 *
 */
public enum UserMemoEnum {
	
	user_block(1 , "查封备注"),
	user_deblock(2 , "解封备注"),
	proxy_block(3 , "代理人查封备注"),
	proxy_deblock(4 , "代理人解封备注"),
	change_username(5 , "修改用户名"),
	;
	
	private int code;
	
	private String name;
	
	private UserMemoEnum(int code , String name) {
		this.code = code;
		this.name = name;
	}
	
	public static UserMemoEnum getByCode(int code) {
		for(UserMemoEnum um : UserMemoEnum.values()) {
			if(um.getCode() == code) return um;
		}
		return null;
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
