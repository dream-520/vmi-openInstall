package com.tigerjoys.shark.miai.agent.enums;

/**
 * 余额账户日志类型枚举
 * @author chengang
 *
 */
public enum UserBalanceAccountLogTypeEnum {
	
	create_order(1, "支付订单" , 0),
	cancel_order(2 , "取消订单" , 1),
	reject_order(3 , "服务者拒绝订单" , 1),
	paid_appoint_order_status_error(4 , "付费约订单状态异常退款" , 1),
	withdrawal_order(5 , "收益提现" , 1),
	buyer_cancel_order(7 , "卖家撤销订单" , 1),
	paid_appoint_audit_nopass_refound(8 , "付费约审核未通过退还" , 1),
	user_create(9 , "新用户注册赠送" , 1),
	cancel_order_fare(10 , "取消订单退还车费" , 1),
	pay_svip(11 , "开通高级会员" , 1),
	cancel_dispatch(12 , "取消派单" , 1),
	//不要超过这个值
	test(100 , "测试" , 1),
    ;
	
	private final int code;
	private final String desc;
	private final int logType;//-1不好归类,0支出,1收入
	
	private UserBalanceAccountLogTypeEnum(int code, String desc , int logType) {
		this.code = code;
		this.desc = desc;
		this.logType = logType;
	}

	public static String getDescByCode(int code) {
		for (UserBalanceAccountLogTypeEnum refer : UserBalanceAccountLogTypeEnum.values())
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

	public int getLogType() {
		return logType;
	}

	@Override
	public String toString() {
		return "{code="+code+",desc="+desc+",logType="+logType+"}";
	}

}
