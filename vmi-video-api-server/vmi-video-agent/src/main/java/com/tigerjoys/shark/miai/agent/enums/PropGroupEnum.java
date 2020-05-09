package com.tigerjoys.shark.miai.agent.enums;

/**
 * 道具分类枚举类
 * @author lipeng
 *
 */
public enum PropGroupEnum {
	other(0,"其他"),
	consume(1,"魔法道具");
	
	private int code;
	private String desc;
	
	private PropGroupEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PropGroupEnum refer : PropGroupEnum.values())
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
