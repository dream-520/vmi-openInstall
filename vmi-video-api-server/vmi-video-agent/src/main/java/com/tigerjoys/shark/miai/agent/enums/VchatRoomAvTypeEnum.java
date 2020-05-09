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
 * @author lvshouyang 
 * @version  
 * @since JDK 1.8.0 
 */
public enum VchatRoomAvTypeEnum {

	audio(1,"音频"),
	video(2,"视频"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(VchatRoomAvTypeEnum refer : VchatRoomAvTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private VchatRoomAvTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
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
