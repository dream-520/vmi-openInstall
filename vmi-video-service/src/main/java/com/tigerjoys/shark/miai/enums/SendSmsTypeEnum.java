package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送枚举
 * @author lipeng
 *
 */
public enum SendSmsTypeEnum {
	
	//枚举
	custom(0 , "自定义短信"),
	regist(1 , "注册发送验证码"),
	forget_pass(2 , "忘记密码发送验证码"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> ERR_DESC = new HashMap<Integer , String>();
	
	static {
		for(SendSmsTypeEnum refer : SendSmsTypeEnum.values()) {
			ERR_DESC.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private SendSmsTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return ERR_DESC.get(code);
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
