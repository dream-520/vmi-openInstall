package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代金券类型枚举
 * @author yangjunming
 *
 */
public enum ShortVideoTranscodeStatusEnum {
	
	uncommit(0,"未提交"),
	transcodeing(1,"正在转码"),
	trans_success(2,"转码成功"),
	trans_fail(3,"转码失败"),
	trans_timeout(4,"转码超时"),
 	;
	
	/**
	 * 状态
	 */
	private int code;
	/**
	 * 状态描述
	 */
	private String desc;
	
	private ShortVideoTranscodeStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	private static final Map<Integer , ShortVideoTranscodeStatusEnum> code_Enum = new HashMap<Integer , ShortVideoTranscodeStatusEnum>();
	
	static {
		for(ShortVideoTranscodeStatusEnum refer : ShortVideoTranscodeStatusEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
			code_Enum.put(refer.getCode(), refer);
		}
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
	}
	
	public static ShortVideoTranscodeStatusEnum getEnumByCode(int code) {
		return code_Enum.get(code);
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
