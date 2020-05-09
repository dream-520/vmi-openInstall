package com.tigerjoys.shark.miai.enums;

/**
 * 道具使用范围枚举类
 * @author lipeng
 * code 2进制写法 111 自己 好友 陌生人 可用为1 不可用为0 转10进制 例 111转10进制为7
 */
public enum PropUseEnum {
	//
	friend_use(01000,"好友可用"),//010
	own_friend_use(6,"自己、好友可用");//110
	
	private int code;
	private String desc;
	
	private PropUseEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PropUseEnum refer : PropUseEnum.values()) {
			if (code == refer.getCode()) {
				return refer.getDesc();
			}
		}
		return null;
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
