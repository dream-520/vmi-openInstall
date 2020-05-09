package com.tigerjoys.shark.miai.enums;

/**
 * 提現状态类型
 * @author chengang
 *
 */
public enum UserFriendStatusEnum {
	//
	UNKNOWN(-1,"未知"),
	NO(0, "没关系"),
	FANS(1 , "他是我的粉丝"),
	FOLLOW(2 , "我关注他了"),
	FRIEND(3 , "好友"),
    ;
	
	private int code;
	private String desc;
	
	private UserFriendStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (UserFriendStatusEnum refer : UserFriendStatusEnum.values()) {
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
