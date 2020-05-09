package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息感情状况枚举类
 * @author lipeng
 *
 */
public enum MarriageEnum {
	
	default_null(0,""),
	single(1,"单身"),
	love(2,"恋爱"),
	married(3,"已婚"),
	separation(4,"分居"),
	divorced(5,"离异"),
 	;
	
	/**
	 * 分享回调结果代码
	 */
	private int code;
	/**
	 * 任务描述
	 */
	private String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(MarriageEnum refer : MarriageEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private MarriageEnum(int code, String desc) {
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
