package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型枚举类
 * @author liuman
 *
 */
public enum UserTypeEnum {
	
	ordinary(1,"普通用户"),
	servicer(2,"服务者"),
 	;
	
	/**
	 * 用户类型代码
	 */
	private int code;
	/**
	 * 用户类型描述
	 */
	private String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	
	static {
		for(UserTypeEnum refer : UserTypeEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
