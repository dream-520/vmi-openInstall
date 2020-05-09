package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 魔法道具枚举类
 * @author lipeng
 *
 */
public enum MagicPropEnum {
	gold(1,"金系魔法"),
	wood(2,"木系魔法"),
	water(3,"水系魔法"),
	fire(4,"火系魔法"),
	soil(5,"土系魔法");
	
	private int code;
	private String desc;
	
	private MagicPropEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> magic_desc = new HashMap<Integer , String>();
	
	
	static {
		for(MagicPropEnum refer : MagicPropEnum.values()) {
			magic_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	
	public static String getDescByCode(int code) {
		return magic_desc.get(code);
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
