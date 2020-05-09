package com.tigerjoys.shark.miai.agent.enums;

/**
 * android的app页面类型枚举
 * @author liuman
 *
 */
public enum AndroidAppEnum {
	
	bussiness_page(0,"BusMsgFragment","业务消息列表"),
	my_publish(1,"ServiceOrderOwn","我发布的"),
	my_purchase(2,"ServiceOrderBuy","我购买的"),
	my_paid_appointment_detail(3,"ServiceDetailFragment","付费约详情"),
	//TODO 
	;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * android的app页代码
	 */
	private String code;
	/**
	 * 页面描述
	 */
	private String desc;

	private AndroidAppEnum(int type, String code, String desc) {
		this.type = type;
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int type) {
		for (AndroidAppEnum refer : AndroidAppEnum.values())
			if (type == refer.getType())
				return refer.getDesc();
		return null;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
