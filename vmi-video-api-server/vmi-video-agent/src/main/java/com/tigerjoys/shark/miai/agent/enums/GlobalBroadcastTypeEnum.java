package com.tigerjoys.shark.miai.agent.enums;

/**
 * 全局广播类型枚举
 * @author lipeng
 *
 */
public enum GlobalBroadcastTypeEnum {
	
	gift(1 , "礼物"),
	recharge(2 , "充值"),
	online( 3, "上线"),
	;
	
	private int code;
	private String desc;
	
	private GlobalBroadcastTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
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
