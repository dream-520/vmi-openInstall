package com.tigerjoys.shark.miai.agent.enums;

public enum PushChannel {
	uninstall(-2 , "卸载"),
	unknown(-1 , "未知"),
	xiaomiPush(0 , "小米推送"),
	huaweiPush(1 , "华为推送"),
	vivoPush(2, "Vivo推送"),
	;
	
	private int code;
	private String desc;
	
	private PushChannel(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static PushChannel getByCode(int code) {
		for(PushChannel uf : PushChannel.values()) {
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
