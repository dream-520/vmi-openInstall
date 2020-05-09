package com.tigerjoys.shark.miai.agent.enums;

/**
 * 高级会员等级
 * @author chengang
 *
 */
public enum UserSVIPLevelEnum {
	
	NORMAL_LEVEL(0 , "普通用户" , 0 , 1 , "/img/talent/talent1.png","你是普通用户,升级金卡》:"),
	GOLD_LEVEL(1 , "金卡" , 3000*100 , 2 , "/img/talent/talent2.png","你是金卡,升级钻卡》:"),
	DIAMOND_LEVEL(2 , "钻卡" , 10000*100 , 3 , "/img/talent/talent3.png","你是钻卡,升级黑卡》:"),
	BLACK_LEVEL(3 , "至尊黑卡" , 50000*100 , 0 , "/img/talent/talent4.png","你是至尊黑卡")
	;
	
	/**
	 * 等级ID
	 */
	private final int level;
	
	/**
	 * 等级名称
	 */
	private final String name;
	
	/**
	 * 获取充值金额限制(单位：分)
	 */
	private final int conditionAmount;
	
	/**
	 * 下一等级,0为无下一等级
	 */
	private final int nextLevel;
	
	/**
	 * 图标
	 */
	private final String icon;
	
	/**
	 * 卡级别描述
	 */
	private final String svipDesc;
	
	private UserSVIPLevelEnum(int level , String name , int conditionAmount , int nextLevel , String icon, String svipDesc) {
		this.level = level;
		this.name = name;
		this.conditionAmount = conditionAmount;
		this.nextLevel = nextLevel;
		this.icon = icon;
		this.svipDesc = svipDesc;
	}
	
	public static UserSVIPLevelEnum getByCode(int level) {
		for (UserSVIPLevelEnum refer : UserSVIPLevelEnum.values())
			if (level == refer.level)
				return refer;
		return null;
	}

	public static String getNameByCode(int code) {
		UserSVIPLevelEnum e = getByCode(code);
		return e != null ? e.name : null;
	}
	
	/**
	 * 检查充值金额对应的SVIP等级
	 * @param totalAmount - 一年内的充值总金额
	 * @return UserSVIPLevelEnum
	 */
	public static UserSVIPLevelEnum checkSvipLevel(long totalAmount) {
		UserSVIPLevelEnum highLevel = null;
		for(UserSVIPLevelEnum level : UserSVIPLevelEnum.values()) {
			if(totalAmount >= level.getConditionAmount()) {
				highLevel = level;
			} else {
				break;
			}
		}
		
		return highLevel == null ? UserSVIPLevelEnum.NORMAL_LEVEL : highLevel;
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public int getConditionAmount() {
		return conditionAmount;
	}

	public int getNextLevel() {
		return nextLevel;
	}

	public String getIcon() {
		return icon;
	}

	public String getSvipDesc() {
		return svipDesc;
	}

}
