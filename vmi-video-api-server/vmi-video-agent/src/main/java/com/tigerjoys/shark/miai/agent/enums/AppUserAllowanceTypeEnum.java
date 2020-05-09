package com.tigerjoys.shark.miai.agent.enums;

/**
 * 平台补助类型
 * @author shiming
 *
 */
public enum AppUserAllowanceTypeEnum {

	no(0 , "不进行任何补助"),
	diamond(1 , "补助钻石"),
	money(2 , "补助收益抵扣"),
	experienceCard(3 , "赠送体验卡"),
	;
	
	private int code;
	private String desc;
	
	private AppUserAllowanceTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据code获得平台补助类型枚举对象
	 * @param code - int
	 * @return UserFrEnum
	 */
	public static AppUserAllowanceTypeEnum getByCode(int code) {
		for(AppUserAllowanceTypeEnum uf : AppUserAllowanceTypeEnum.values()) {
			if(uf.getCode() == code) 
				return uf;
		}
		return no;
	}
	
	/**
	 * 根据code获得描述信息
	 * @param code - int
	 * @return String
	 */
	public static String getDescByCode(int code) {
		return getByCode(code).getDesc();
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
