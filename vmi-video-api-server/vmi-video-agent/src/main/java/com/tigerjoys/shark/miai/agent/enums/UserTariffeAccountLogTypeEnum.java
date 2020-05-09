/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: UserTariffeAccountLogTypeEnum <br/> 
 * date: 2019年12月10日 下午7:04:44 <br/> 
 * 
 * @author lipeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserTariffeAccountLogTypeEnum {

	unknown(0,"未知"),
	vip_give(1, "购买vip获得话费"),
	tariffe_recharge(2 , "话费充值"),
	//不要超过这个值
	test(100 , "测试"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> descs = new HashMap<Integer , String>();
	
	static {
		for(UserTariffeAccountLogTypeEnum refer : UserTariffeAccountLogTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserTariffeAccountLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static UserTariffeAccountLogTypeEnum getByCode(int code) {
		for (UserTariffeAccountLogTypeEnum refer : UserTariffeAccountLogTypeEnum.values())
			if (code == refer.getCode())
				return refer;
		return unknown;
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
