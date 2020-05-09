package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 举报类型枚举类
 * @author lipeng
 *
 */
public enum InformTypeEnum {
	
	ad_cheat(0,"广告欺诈"),
	obscenity(1,"淫秽色情"),
	harass_rail(2,"骚扰谩骂"),
	political_reaction(3,"反动政治"),
	other_guide(4,"其他内容"),
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
		for(InformTypeEnum refer : InformTypeEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private InformTypeEnum(int code, String desc) {
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
