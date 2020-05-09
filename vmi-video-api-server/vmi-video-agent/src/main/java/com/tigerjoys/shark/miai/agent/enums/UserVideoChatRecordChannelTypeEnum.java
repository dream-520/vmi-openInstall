/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: AccountLogTypeEnum <br/> 
 * date: 2017年5月10日 下午7:04:44 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserVideoChatRecordChannelTypeEnum {

	video_yunxin(1, "网易视频"),
	video_tencent(2, "腾讯视频"),
	
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	private static final Map<Integer , UserVideoChatRecordChannelTypeEnum> channelTypeMap = new HashMap<Integer , UserVideoChatRecordChannelTypeEnum>();
	
	static {
		for(UserVideoChatRecordChannelTypeEnum refer : UserVideoChatRecordChannelTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserVideoChatRecordChannelTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
	}
	
	public static UserVideoChatRecordChannelTypeEnum getByCode(int code) {
		return channelTypeMap.get(code);
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
