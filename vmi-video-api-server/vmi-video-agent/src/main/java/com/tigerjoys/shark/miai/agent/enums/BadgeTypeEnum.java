package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
  * @author mouzhanpeng at [2017年11月14日 上午10:39:33] 
  * @since JDK 1.8.0 
  */
public enum BadgeTypeEnum {
	
	NO_PROMISE(0,"无徽章"),
	LOW_PROMISE(1,"一言为定"),
	MID_PROMISE(2,"一诺千金"),
	HIGH_PROMISE(3,"一言九鼎");
	
	private int code;
	private String desc;
	
	private static final Map<Integer, BadgeTypeEnum> bt = new HashMap<>();
	
	static {
		for(BadgeTypeEnum refer : BadgeTypeEnum.values()) {
			bt.put(refer.getCode(), refer);
		}
	}
	
	private BadgeTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static BadgeTypeEnum getByCode(int code) {
		BadgeTypeEnum em = bt.get(code);
		if(em == null) {
			return null;
		}
		return em;
	}
	
	public static String getDescByCode(int code) {
		return getByCode(code).getDesc();
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
