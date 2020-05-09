package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息对感情的看法枚举类
 * @author lipeng
 *
 */
public enum SexOpinionEnum {
	
	default_null(0,""),
	conservative(1,"保守"),
	conservative_asexual(2,"保守且无经历"),
	let_it_be(3,"顺其自然"),
	resonance_between_two_lovers(4,"两情相悦"),
	open(5,"开放"),
	crazy(6,"疯狂"),
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
		for(SexOpinionEnum refer : SexOpinionEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private SexOpinionEnum(int code, String desc) {
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
