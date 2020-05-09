package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代金券类型枚举
 * @author yangjunming
 *
 */
public enum CouponStatusEnum {
	
	available(0,"未使用"),
	Used(1,"已使用"),
	out_of_date(2,"已过期"),
 	;
	
	/**
	 * 类型
	 */
	private int code;
	/**
	 * 类型描述
	 */
	private String desc;
	
	private CouponStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(CouponStatusEnum refer : CouponStatusEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
	}
	
	
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public static Map<Integer, String> getCodeDesc() {
		return code_desc;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
