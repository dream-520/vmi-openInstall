package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态请求类型的枚举
 * @author yangjunming
 *
 */
public enum VchatRoomFreeVchartFalgEnum {
	//
	no_free(0,"不免费"),
	free_vchart (1,"免费聊"),
	free_vchart_charge (2,"免费聊充值"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , VchatRoomFreeVchartFalgEnum> STATUS_DESC = new HashMap<Integer , VchatRoomFreeVchartFalgEnum>();
	
	static {
		for(VchatRoomFreeVchartFalgEnum refer : VchatRoomFreeVchartFalgEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private VchatRoomFreeVchartFalgEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static VchatRoomFreeVchartFalgEnum getByCode(int code) {
		VchatRoomFreeVchartFalgEnum errCode = STATUS_DESC.get(code);
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
