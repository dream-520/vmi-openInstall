package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
  * @author mouzhanpeng at [2017年11月27日 上午11:42:27] 
  * @since JDK 1.8.0 
  */
public enum TaskCategoryEnum {

	INFORMATION(1, "完善信息", "完整填写基本信息、详细信息、兴趣爱好信息。"),
	REGISTER(2, "注册达人", "注册成为达人，通过审核。"),
	IMAGE(3, "发布图片动态", "发布图片动态。"),
	VIDEO(4, "发布视频动态", "发布视频动态。"),
	CHAT(5, "聊天", "和其他用户进行聊天。"),
	PHOTOS(6, "发布相册", "在我的相册中添加图片。"),
	APPOINT(7, "发布技能", "发布普通技能。"),
	TALENT(8, "技能订单", "每成功达成一次技能订单有额外现金奖励。"),
	SHARE(9, "分享好友", "成功邀请好友注册软件。");
	
	private int code;
	private String title;
	private String description;
	
	private static final Map<Integer, TaskCategoryEnum> tc = new HashMap<>();
	
	static {
		for(TaskCategoryEnum refer : TaskCategoryEnum.values()) {
			tc.put(refer.getCode(), refer);
		}
	}
	
	private TaskCategoryEnum(int code, String title, String desc) {
		this.code = code;
		this.title = title;
		this.description = desc;
	}
	
	public static TaskCategoryEnum getByCode(int code) {
		return tc.get(code);
	}
	
	public int getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
