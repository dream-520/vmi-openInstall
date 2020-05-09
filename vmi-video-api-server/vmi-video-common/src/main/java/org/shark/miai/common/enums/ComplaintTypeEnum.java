package org.shark.miai.common.enums;

/**
 * 投诉人类型枚举类(付费约会和非付费用户通用)
 * @author liuman
 */
public enum ComplaintTypeEnum {
	
	customer(1,"客户"),
	servicer(2,"服务者");
	
	private int code;
	private String desc;
	
	private ComplaintTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (ComplaintTypeEnum refer : ComplaintTypeEnum.values())
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
