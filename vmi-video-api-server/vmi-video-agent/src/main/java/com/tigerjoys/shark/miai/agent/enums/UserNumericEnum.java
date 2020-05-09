package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户数值更新枚举
 * @author chengang
 *
 */
public enum UserNumericEnum {
	
	unknown(0 , "未知"),
	pick_red_packet(1,"摘取红包"),
	steal_red_packet(2,"偷取红包"),
	tree_level_up(3 , "红包树升级"),
	finish_task(4 , "完成任务奖励"),
	buy_prop(5 , "购买道具"),
	use_prop(6 , "使用道具"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(UserNumericEnum refer : UserNumericEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserNumericEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		String desc = err_desc.get(code);
		if(desc == null) return unknown.desc;
		return desc;
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
