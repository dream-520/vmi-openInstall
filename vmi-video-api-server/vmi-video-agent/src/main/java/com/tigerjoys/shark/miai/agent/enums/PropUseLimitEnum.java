package com.tigerjoys.shark.miai.agent.enums;

/**
 * 魔法道具枚举类
 * @author lipeng
 *
 */
public enum PropUseLimitEnum {
	own("10000","自己"),//11000    第一位 自己 ，第二位 好友 第三位 陌生人 其他为预留
	friend("01000","好友"),
	own_friend("11000","自己、好友"),
	all("111000","任何人");
	
	private String code;
	private String desc;
	
	private PropUseLimitEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		for (PropUseLimitEnum refer : PropUseLimitEnum.values())
			if (code.equals(refer.getCode()))
				return refer.getDesc();
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
