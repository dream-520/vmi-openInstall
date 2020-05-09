package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 奖口类型枚举
 * @author yangjunming
 *
 */
public enum RewardTypeEnum {
	
	goldCoin(1,"金币"),
	cash(2,"现金"),
	flow(3,"流量币"),
	growth(4,"成长值"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> type_desc = new HashMap<Integer , String>();
	
	static {
		for(RewardTypeEnum refer : RewardTypeEnum.values()) {
			type_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private RewardTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return type_desc.get(code);
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
