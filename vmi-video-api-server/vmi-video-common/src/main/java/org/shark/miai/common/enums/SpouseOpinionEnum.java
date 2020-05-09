package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息对另一半的要求枚举类
 * @author lipeng
 *
 */
public enum SpouseOpinionEnum {
	
	
	default_null(0,""),
	longleg(1,"长腿"),
	chubby(2,"微胖"),
	can_swim(3,"会游泳"),
	bingle(4,"短发"),
	shape(5,"身材好"),
	big_breasts(6,"小鲜肉"),
	oxeye(7,"大眼睛"),
	good_work(8,"成熟"),
	risibility(9,"无要求"),
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
		for(SpouseOpinionEnum refer : SpouseOpinionEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private SpouseOpinionEnum(int code, String desc) {
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
