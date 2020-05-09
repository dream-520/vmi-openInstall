package com.tigerjoys.shark.miai.agent.enums;

/**
 * 道具功能枚举类
 * @author lipeng
 *
 */
public enum PropFunctionEnum {
	
	
	grow(1,"成长值" ,"","+"),
	time(2,"剩余时间" ,"%","*"),
	gold(3,"金币" ,"%","*"),
	cash(4,"现金" ,"%","*");
	
	private int code;
	private String desc;
	private String unit;
	private String opt;
	
	
	private PropFunctionEnum(int code, String desc ,String unit ,String opt) {
		this.code = code;
		this.desc = desc;
		this.unit = unit;
		this.opt = opt;
	}

	public static String getDescByCode(int code) {
		for (PropFunctionEnum refer : PropFunctionEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
		return null;
	}
	
	public static PropFunctionEnum getByCode(int code) {
		for(PropFunctionEnum pf : PropFunctionEnum.values()) {
			if(pf.getCode() == code) return pf;
		}
		return null;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}
	
}
