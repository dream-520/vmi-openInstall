package org.shark.miai.common.enums;

/**
 * 是否允许发布普通约会
 * @author liuman
 *
 */
public enum FreeApplyAppointRightEnum {
	permit(1 ,"申请普通约会成功!"),
	forbid(2 ,"不允许发布普通约会!"),
	;
	
	private int type;
	private String desc;

	private FreeApplyAppointRightEnum(int type,String desc) {
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
