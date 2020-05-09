package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态请求类型的枚举
 * @author shiming
 *
 */
public enum DynamicReqTypeEnum {
	//
	favor(1,"关注人的动态"),
	news(2,"最新动态"),
	ta (3,"他的动态"),
	me (4,"我的动态"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , DynamicReqTypeEnum> STATUS_DESC = new HashMap<Integer , DynamicReqTypeEnum>();
	
	static {
		for(DynamicReqTypeEnum refer : DynamicReqTypeEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private DynamicReqTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static DynamicReqTypeEnum getByCode(int code) {
		DynamicReqTypeEnum errCode = STATUS_DESC.get(code);
		if(errCode == null) {
			return null;
		}
		return errCode;
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
