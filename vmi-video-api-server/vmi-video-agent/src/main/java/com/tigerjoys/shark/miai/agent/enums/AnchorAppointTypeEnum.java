package com.tigerjoys.shark.miai.agent.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 主播的约会类型
 */
public enum AnchorAppointTypeEnum {

	all(0,"全部"),
	food(1, "美食"),
	movie(2 , "电影"),
	street(3 , "逛街"),
	paly(4 , "游玩"),
	sing(5, "唱歌"),
	sport(6, "运动"),
	bar(7, "泡吧"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> descs = new HashMap<Integer , String>();
	private static final List<Integer> appointType = new ArrayList<Integer>();
	
	static {
		for(AnchorAppointTypeEnum refer : AnchorAppointTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
			appointType.add(refer.getCode());
		}
	}
	
	public static List<Integer> getAppointtype() {
		return appointType;
	}

	private AnchorAppointTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static AnchorAppointTypeEnum getByCode(int code) {
		for (AnchorAppointTypeEnum refer : AnchorAppointTypeEnum.values())
			if (code == refer.getCode())
				return refer;
		return all;
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
