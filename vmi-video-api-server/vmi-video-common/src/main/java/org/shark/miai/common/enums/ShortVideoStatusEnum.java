package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代金券类型枚举
 * @author yangjunming
 *
 */
public enum ShortVideoStatusEnum {
	
	online(1,"上架中"),
	transcoding(2,"视频转码"),
	audit(3,"审核中"),
	offline_initiative(0,"下架中"),
	offline_passive(-1,"下架中"),
	test_data(-2,"提审数据"),
	delete(-9,"删除"),
 	;
	
	/**
	 * 状态
	 */
	private int code;
	/**
	 * 状态描述
	 */
	private String desc;
	
	private ShortVideoStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(ShortVideoStatusEnum refer : ShortVideoStatusEnum.values()) {
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
