package com.tigerjoys.shark.miai.enums;

/**
 * 用户修改自己发布普通约会信息方式枚举类
 * @author liuman
 *
 */
public enum FreeAppointUpdateItemEnum {
	//
	refresh(1,"刷新约会"),
	close(2,"关闭约会"),
    ;
	
	private int code;
	private String desc;
	
	private FreeAppointUpdateItemEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (FreeAppointUpdateItemEnum refer : FreeAppointUpdateItemEnum.values()) {
			if (code == refer.getCode()) {
				return refer.getDesc();
			}
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

}
