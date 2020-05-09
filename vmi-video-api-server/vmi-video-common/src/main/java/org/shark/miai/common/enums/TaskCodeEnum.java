package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务代码枚举
 * @author liuman
 *
 */
public enum TaskCodeEnum {
	
	novice_task_first_recharge("firstCharge","首次充值"),
	everyday_task_daily_gold("loginGold","天天领金币"),
	everyday_task_steal_red_envelopes("stealRedEnvelopes","天天偷红包"),
	everyday_task_share_link("shareLink","邀请好友"),
	write_wish("writeWish","写心愿"),
 	;
	
	/**
	 * 任务代码
	 */
	private String code;
	/**
	 * 任务描述
	 */
	private String desc;
	
	private static final Map<String , String> code_desc = new HashMap<String , String>();
	
	
	static {
		for(TaskCodeEnum refer : TaskCodeEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private TaskCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(String code) {
		return code_desc.get(code);
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
