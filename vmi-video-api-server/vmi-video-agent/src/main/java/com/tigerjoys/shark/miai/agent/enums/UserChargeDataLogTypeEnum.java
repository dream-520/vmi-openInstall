package com.tigerjoys.shark.miai.agent.enums;

/**
 * 用户充值记录日志类型枚举
 * @author chengang
 *
 */
public enum UserChargeDataLogTypeEnum {
	
	paid_appoint_order(1, "支付达人秀订单"),
	paidan_order(2 , "支付派单订单"),
	charge_diamond(3 , "充值钻石"),
	//不要超过这个值
	test(100 , "测试"),
    ;
	
	private final int code;
	private final String desc;
	
	private UserChargeDataLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (UserChargeDataLogTypeEnum refer : UserChargeDataLogTypeEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "{code="+code+",desc="+desc+"}";
	}

}
