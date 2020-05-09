package com.tigerjoys.shark.miai.agent.enums;

/**
 * 用户登录日志类型
 * @author chengang
 *
 */
public enum UserLoginLogTypeEnum {
	
	login(1,"登录"),
	position_change(2,"位置更改"),
	login_out(3 , "退出登录"),
	register(4 , "注册"),
    ;
	
	private int code;
	private String desc;
	
	private UserLoginLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (UserLoginLogTypeEnum refer : UserLoginLogTypeEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
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
