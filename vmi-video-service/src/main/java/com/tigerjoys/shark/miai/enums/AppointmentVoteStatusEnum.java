package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态请求类型的枚举
 * @author yangjunming
 *
 */
public enum AppointmentVoteStatusEnum {
	//
	can_vote(0,"可投"),
	await_win (1,"投注已满"),
	win (2,"开奖"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , AppointmentVoteStatusEnum> STATUS_DESC = new HashMap<Integer , AppointmentVoteStatusEnum>();
	
	static {
		for(AppointmentVoteStatusEnum refer : AppointmentVoteStatusEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private AppointmentVoteStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static AppointmentVoteStatusEnum getByCode(int code) {
		AppointmentVoteStatusEnum errCode = STATUS_DESC.get(code);
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
