package com.tigerjoys.shark.miai.agent.enums;

/**
 * yoyou活动任务主题枚举类
 * @author liuman
 *
 */
public enum PushTopicEnum {
	perfect_personal_data(0,"topic_perfect"),
	;
	/**
	 * 主题代码code
	 */
	private int code;
	/**
	 * 主题名称
	 */
	private String desc;

	private PushTopicEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (PushTopicEnum refer : PushTopicEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
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
