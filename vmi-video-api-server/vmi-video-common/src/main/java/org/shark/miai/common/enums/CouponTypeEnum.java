package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代金券类型枚举
 * @author yangjunming
 *
 */
public enum CouponTypeEnum {
	
	paidAppoint(0,"付费约"),
	
 	;
	
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 类型描述
	 */
	private String desc;
	
	private CouponTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(CouponTypeEnum refer : CouponTypeEnum.values()) {
			code_desc.put(refer.getType(), refer.getDesc());
		}
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
