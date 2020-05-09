package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云短信签名
 * @author yangjunming
 *
 */
public enum AlibabaSmsSignEnum {
	com_ydwx_yoyo("com.ydwx.yoyo","V密"),
	com_ydwx_yoyo3("com.ydwx.yoyo3","密约"),
	com_tjhj_miliao("com.tjhj.miliao","蜜聊"),

 	;
	
	private String channel;
	private String desc;
	
	private static final Map<String , String> CHANNEL_DESC = new HashMap<String , String>();
	
	static {
		for(AlibabaSmsSignEnum refer : AlibabaSmsSignEnum.values()) {
			CHANNEL_DESC.put(refer.channel, refer.getDesc());
		}
	}
	
	private AlibabaSmsSignEnum(String channel, String desc) {
		this.channel = channel;
		this.desc = desc;
	}
	
	public static String getByDesc(String path) {
		String desc = CHANNEL_DESC.get(path);
		if(desc == null) {
			return null;
		}
		return desc;
	}
	

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
