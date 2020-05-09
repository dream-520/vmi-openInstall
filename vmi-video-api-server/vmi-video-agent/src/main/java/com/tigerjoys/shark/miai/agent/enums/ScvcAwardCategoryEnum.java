package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
  * @author mouzhanpeng at [2017年11月27日 上午11:42:27] 
  * @since JDK 1.8.0 
  */
public enum ScvcAwardCategoryEnum {

	ONLINE(1, "在线时长奖励", "一天活跃时长超过10分钟，奖励%d个。", "今天你很活跃哦，奖励你%d个SCVC积分。", 10),
	RECHARGE(2, "充值奖励", "每次充值成功，奖励%d个。", "充值成功，奖励你%d个SCVC积分。", 10),
	BANG(3, "参加帮帮奖励", "申请成功，奖励%d个。", "申请帮帮成功，奖励你%d个SCVC积分。(每天只奖励一次)", 10),
	SKILL(4, "购买达人秀奖励", "下单成功，奖励%d个。", "达人秀下单成功，奖励你%d个SCVC积分。(每天只奖励一次)", 10),
	VIDEO(5, "视频聊天奖励", "每次视频聊天超过10分钟，奖励%d个。", "今天你很活跃哦，奖励你%d个SCVC积分。", 10),
	TASK(6, "任务附加奖励", "", "", 0), //奖励额因任务而异
	LUCKY(7, "幸运抽奖奖励", "", "", 10),
	;
	private int code;
	private String title;
	private String description;
	// 推送消息内容
	private String msg;
	// 奖励额度
	private int award;
	
	private static final Map<Integer, ScvcAwardCategoryEnum> tc = new HashMap<>();
	
	static {
		for(ScvcAwardCategoryEnum refer : ScvcAwardCategoryEnum.values()) {
			tc.put(refer.getCode(), refer);
		}
	}
	
	private ScvcAwardCategoryEnum(int code, String title, String desc, String msg, int award) {
		this.code = code;
		this.title = title;
		this.description = desc;
		this.msg = msg;
		this.award = award;
	}
	
	public static ScvcAwardCategoryEnum getByCode(int code) {
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

	public String getMsg() {
		return msg;
	}

	public int getAward() {
		return award;
	}

}
