package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息交友目的枚举类
 * @author lipeng
 *
 */
public enum MakeFriendEnum {
	
	default_null(0,""),
	find_friends(1,"寻找知己玩伴"),
	find_love(2,"找寻恋爱对象"),
	only_married(3,"以结婚为目的"),
	find_king(4,"两情相悦"),
	fastsex(5,"饮食男女"),
	find_playmate(6,"找玩伴"),
 	;
	
	/**
	 * 分享回调结果代码
	 */
	private int code;
	/**
	 * 任务描述
	 */
	private String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(MakeFriendEnum refer : MakeFriendEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private MakeFriendEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
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
