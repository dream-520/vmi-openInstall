package org.shark.miai.common.enums;

/**
 * 是否允许发布普通约会
 * @author liuman
 *
 */
public enum PublishFreeAppointRightEnum {
	
	permit(1 ,"允许发布普通约会!"),
	forbid(2 ,"不允许发布普通约会!"),
	;
	
	private int type;
	private String desc;

	private PublishFreeAppointRightEnum(int type,String desc) {
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
