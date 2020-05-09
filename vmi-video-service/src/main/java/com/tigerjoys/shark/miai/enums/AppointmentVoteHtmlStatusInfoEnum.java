package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态请求类型的枚举
 * @author shiming
 *
 */
public enum AppointmentVoteHtmlStatusInfoEnum {
	//
	no_vote(0,"参与"),
	already_vote(1,"已投，等待揭晓"),
	await_win (2,"等待揭晓"),
	win (3,"开奖信息"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , AppointmentVoteHtmlStatusInfoEnum> STATUS_DESC = new HashMap<Integer , AppointmentVoteHtmlStatusInfoEnum>();
	
	static {
		for(AppointmentVoteHtmlStatusInfoEnum refer : AppointmentVoteHtmlStatusInfoEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private AppointmentVoteHtmlStatusInfoEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static AppointmentVoteHtmlStatusInfoEnum getByCode(int code) {
		AppointmentVoteHtmlStatusInfoEnum errCode = STATUS_DESC.get(code);
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
