package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态类型的枚举
 * @author shiming
 *
 */
public enum DynamicTypeEnum {
	
	//图片
	picture(1,"图片类型的动态"),
	video(2,"视频类型的动态"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , DynamicTypeEnum> STATUS_DESC = new HashMap<Integer , DynamicTypeEnum>();
	
	static {
		for(DynamicTypeEnum refer : DynamicTypeEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private DynamicTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static DynamicTypeEnum getByCode(int code) {
		DynamicTypeEnum errCode = STATUS_DESC.get(code);
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
