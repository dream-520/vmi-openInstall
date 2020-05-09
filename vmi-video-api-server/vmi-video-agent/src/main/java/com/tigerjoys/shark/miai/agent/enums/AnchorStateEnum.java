package com.tigerjoys.shark.miai.agent.enums;

public enum AnchorStateEnum {

	apply(0,"申请状态"),
	pass(1,"审核通过成为大v"),
	reject(-1, "申请拒绝"),
	undercarriage(-8, "下架中")
    ;
	
	private int code;
	private String desc;
	
	private AnchorStateEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (AnchorOnlineStateEnum refer : AnchorOnlineStateEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
		return null;
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
