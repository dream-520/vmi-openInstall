package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息收入枚举类
 * @author lipeng
 *
 */
public enum IncomeEnum {
	
	default_null(0,""),
	income1(1,"3000元以上/月"),
	income2(2,"5000元以上/月"),
	income3(3,"10000元以上/月"),
	income4(4,"20000元以上/月"),
	income5(5,"50万元以上/年"),
	secrecy(6,"保密"),
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
		for(IncomeEnum refer : IncomeEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private IncomeEnum(int code, String desc) {
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
