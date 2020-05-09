package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 分享枚举类
 * @author lipeng
 *
 */
public enum ShareEnum {
	
	share_tree_up(1,"红包树升级"),
	share_pick_my_red_packet(2,"摘取自己的红包"),
	share_pick_other_red_packet(3,"偷取别人的红包"),
	user_grade(4,"等级分享"),
 	;
	
	/**
	 * 领取任务代码
	 */
	private int code;
	/**
	 * 领取任务按钮描述
	 */
	private String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	
	static {
		for(ShareEnum refer : ShareEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private ShareEnum(int code, String desc) {
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
