package com.tigerjoys.shark.miai.agent.enums;

/**
 * 发送假消息的类型
 * @author shiming
 *
 */
public enum ScenceMessageTypeEnum {
	
	unknown(0 , "未知"),
	text(1 , "文本消息"),
	picture(2 , "图片消息"),
	video(3, "视频消息"),
	audio(4 , "音频消息"),
	textPostion(5 , "文本中有位置消息"),
	;
	
	private int code;
	private String desc;
	
	private ScenceMessageTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static ScenceMessageTypeEnum getByCode(int code) {
		for(ScenceMessageTypeEnum uf : ScenceMessageTypeEnum.values()) {
			if(uf.getCode() == code) return uf;
		}
		return unknown;
	}
	
	public static String getDescByCode(int code) {
		return getByCode(code).getDesc();
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
