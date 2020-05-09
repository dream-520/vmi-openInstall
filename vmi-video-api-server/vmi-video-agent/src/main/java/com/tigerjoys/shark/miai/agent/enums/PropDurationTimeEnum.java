package com.tigerjoys.shark.miai.agent.enums;

/**
 * 道具持续次数枚举类
 * @author lipeng
 *
 */
public enum PropDurationTimeEnum {
	other(0,"其他"),
	once(1,"单次");
	
	private int code;
	private String desc;
	
	private PropDurationTimeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PropDurationTimeEnum refer : PropDurationTimeEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
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

}
