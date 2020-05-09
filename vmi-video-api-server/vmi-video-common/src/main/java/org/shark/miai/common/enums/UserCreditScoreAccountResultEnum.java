package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信用账户返回结果枚举
 * @author liuman
 *
 */
public enum UserCreditScoreAccountResultEnum {
	
	success(0,"成功","code"),
	notEnough(1,"余额不足","code"),
	haveBeCall(2,"接口已调用","code"),
	wrongParameter(3,"参数传入错误","code");
	
	/**
	 * 返回状态
	 */
	private int code;
	
	/**
	 * 状态描述
	 */
	private String desc;
	
	/**
	 * 返回对象的key
	 */
	private String key;
	
	private static final Map<Integer , UserCreditScoreAccountResultEnum> err_desc = new HashMap<Integer , UserCreditScoreAccountResultEnum>();
	
	static {
		for(UserCreditScoreAccountResultEnum refer : UserCreditScoreAccountResultEnum.values()) {
			err_desc.put(refer.getCode(), refer);
		}
	}

	private UserCreditScoreAccountResultEnum(int code, String desc , String key) {
		this.code = code;
		this.desc = desc;
		this.key = key;
	}

	public static String getDescByCode(int code) {
		UserCreditScoreAccountResultEnum type = err_desc.get(code);
		if(type == null) return null;
		return type.getDesc();
	}
	
	public static String getMemoByCode(int code) {
		UserCreditScoreAccountResultEnum type = err_desc.get(code);
		if(type == null) return null;
		return type.getKey();
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
