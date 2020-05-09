package com.tigerjoys.shark.miai.agent.enums;

/**
 * 用户注册来源枚举
 * @author chengang
 *
 */
public enum UserFrEnum {
	
	unknown(0 , "未知"),
	weixin(1 , "微信注册"),
	qq(2 , "QQ注册"),
	mobile( 3, "手机注册"),
	customService(4 , "游客注册"),
	weixinH5(5 , "微信H5注册"),
	robot(9 , "微信"),
	robotNew(10 , "QQ"),
	robotNewWireshark(11 , "手机"),
	robotNewManWireshark(12 , "微信"),
	robotIOSApply(13 , "QQ"),
	;
	
	private int code;
	private String desc;
	
	private UserFrEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据code获得用户注册来源枚举对象
	 * @param code - int
	 * @return UserFrEnum
	 */
	public static UserFrEnum getByCode(int code) {
		for(UserFrEnum uf : UserFrEnum.values()) {
			if(uf.getCode() == code) return uf;
		}
		
		return unknown;
	}
	
	/**
	 * 根据code获得描述信息
	 * @param code - int
	 * @return String
	 */
	public static String getDescByCode(int code) {
		return getByCode(code).getDesc();
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
