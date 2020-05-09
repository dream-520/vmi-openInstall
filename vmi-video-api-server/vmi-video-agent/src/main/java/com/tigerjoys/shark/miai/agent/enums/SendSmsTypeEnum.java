package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送枚举
 * @author lipeng
 *
 */
public enum SendSmsTypeEnum {
	unknow		(0 , "未知","未知"),
	auth_mobile		(103610168 , "身份验证验证码","验证码${code}，您正在进行身份验证，打死不要告诉别人哦！"),
	login_mobile	(103610166 , "登录确认验证码","验证码${code}，您正在登录，若非本人操作，请勿泄露。"),
	login_exception	(103610165 , "登录异常验证码","验证码${code}，您正尝试异地登录，若非本人操作，请勿泄露。"),
	regist			(103610164 , "用户注册验证码","验证码${code}，您正在注册成为新用户，感谢您的支持！"),
	modify_pass		(103610163 , "修改密码验证码","验证码${code}，您正在尝试修改登录密码，请妥善保管账户信息。"),
	forget_pass		(104715006 , "忘记密码验证码","验证码${code}，您正在重置密码，若非本人操作，请勿泄露。"),
	modify_user_info(103610162 , "信息变更验证码","验证码${code}，您正在尝试变更重要信息，请妥善保管账户信息。"),
	notices_message (125029905 , "短信通知","你有新的订单，赶紧去查看吧."),
 	;
	
	private int code;
	private String name;
	private String desc;
	
	private static final Map<Integer , SendSmsTypeEnum> smsTypeMap = new HashMap<>();
	
	static {
		for(SendSmsTypeEnum refer : SendSmsTypeEnum.values()) {
			smsTypeMap.put(refer.getCode(), refer);
		}
	}
	
	private SendSmsTypeEnum(int code, String name,String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}
	
	public static SendSmsTypeEnum getByCode(int code) {
		return smsTypeMap.get(code);
	}
	
	public static String getNameByCode(int code) {
		SendSmsTypeEnum type = smsTypeMap.get(code);
		if(type == null) {
			return unknow.getName();
		} else {
			return type.name;
		}
	}
	
	public static String getDescByCode(int code) {
		SendSmsTypeEnum type = smsTypeMap.get(code);
		if(type == null) {
			return unknow.getDesc();
		} else {
			return type.desc;
		}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
