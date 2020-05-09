package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送礼物
 * @author yangjunming
 *
 */
public enum UserChatGiftLogTypeEnum {
	
	unknown(0,"未知"),
	
	video(1, "视频"),
	chat_text(2, "文字"),
	audio(3, "音频"),
	short_video(4, "短视频"),
	
    ;
	
	/**
	 * 编码
	 */
	private final int code;
	
	/**
	 * 描述
	 */
	private final String desc;
	
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(UserChatGiftLogTypeEnum refer : UserChatGiftLogTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserChatGiftLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
	}
	
	public static UserChatGiftLogTypeEnum getByCode(int code) {
		for (UserChatGiftLogTypeEnum refer : UserChatGiftLogTypeEnum.values())
			if (code == refer.getCode())
				return refer;
		return unknown;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
