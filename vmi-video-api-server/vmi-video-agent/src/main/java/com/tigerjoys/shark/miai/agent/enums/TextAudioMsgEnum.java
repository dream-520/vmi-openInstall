package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 短信发送枚举
 * @author lipeng
 *
 */
public enum TextAudioMsgEnum {
	
	unknown(9 , "未知"),
	success(1 , "成功"),
	fail(0 , "失败"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , TextAudioMsgEnum> code_desc = new HashMap<Integer , TextAudioMsgEnum>();
	
	static {
		for(TextAudioMsgEnum refer : TextAudioMsgEnum.values()) {
			code_desc.put(refer.getCode(), refer);
		}
	}
	
	private TextAudioMsgEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		TextAudioMsgEnum textEnum = code_desc.get(code);
		if(Tools.isNull(textEnum)){
			return unknown.getDesc();
		}
		return textEnum.getDesc();
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
