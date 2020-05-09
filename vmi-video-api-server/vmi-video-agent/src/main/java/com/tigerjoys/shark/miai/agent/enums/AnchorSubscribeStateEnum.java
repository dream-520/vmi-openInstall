package com.tigerjoys.shark.miai.agent.enums;

/**
 * 主播预约状态枚举
 * @author shiming
 */
public enum AnchorSubscribeStateEnum {
	
	subscribe(0, "#FFB505", "预约中"),
	fail(1, "#999999", "预约失败"),
	success(2, "#3C76F9", "预约成功"),
    ;
	
	private int code;
	private String color;
	private String desc;
	
	private AnchorSubscribeStateEnum(int code, String color, String desc) {
		this.code = code;
		this.color = color;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (AnchorSubscribeStateEnum refer : AnchorSubscribeStateEnum.values())
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
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
