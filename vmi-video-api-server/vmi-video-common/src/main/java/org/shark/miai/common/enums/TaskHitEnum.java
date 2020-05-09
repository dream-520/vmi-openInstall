package org.shark.miai.common.enums;

/**
 * 未完成任务点击类型枚举
 * @author liuman
 *
 */
public enum TaskHitEnum {
	
	click_not_executable(0,"不可点击"),
	click_jump_to_app(1,"跳转内页"),
	click_jump_to_h5(2,"跳转H5"),
 	;
	
	/**
	 * 任务点击类型
	 */
	private int type;
	/**
	 * 任务点击类型描述
	 */
	private String desc;
	
	private TaskHitEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
