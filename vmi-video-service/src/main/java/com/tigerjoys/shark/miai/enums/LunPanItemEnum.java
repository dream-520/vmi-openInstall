package com.tigerjoys.shark.miai.enums;

/**
 * 轮盘抽奖活动奖品项目枚举
 * @author lipeng
 *
 */
public enum LunPanItemEnum {
		
	experience_vip_7Days(0,7,"体验VIP7天", (short)0),
	diamond_50(1,50,"50钻石",  (short)1),
	diamond_5(2,5,"5钻石",  (short)1),
	voucher_50(3,50,"50元代金券",  (short)2),
	SCVC_10(4,10,"SCVC10币",  (short)3),
	xiexiecanyu(5,0,"谢谢参与",  (short)-1);
	
	private int code;
	private int value;
	private String title;
	private int type;

	private LunPanItemEnum(int code, int value,String title,int type) {
		this.code = code;
		this.value = value;
		this.title = title;
		this.type = type;
	}

	public static LunPanItemEnum getLunPanItemEnumByCode(int code) {
		for (LunPanItemEnum refer : LunPanItemEnum.values())
			if (code == refer.getCode())
				return refer;
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
