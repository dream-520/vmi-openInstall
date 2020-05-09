/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: PayTypeEnum <br/> 
 * date: 2017年8月14日 下午7:03:34 <br/> 
 *  支付种类枚举
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum ProxyInletEnum {

	offline(0,"线下"),
	online(1,"线上"),

	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer,ProxyInletEnum> pc = new HashMap<Integer,ProxyInletEnum>();
	
	static {
		for(ProxyInletEnum refer : ProxyInletEnum.values()) {
			pc.put(refer.getCode(), refer);
		}
	}
	
	private ProxyInletEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static ProxyInletEnum getByCode(int code) {
		ProxyInletEnum errCode = pc.get(code);
		if(errCode == null) {
			return null;
		}
		return errCode;
	}
	
	public static String getDescByCode(int code) {
		return getByCode(code).desc;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
}
