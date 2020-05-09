/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: PayTypeEnum <br/>
 * date: 2017年8月14日 下午7:03:34 <br/>
 * 支付种类枚举
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public enum PayTypeEnum {
	unknown(0, "未知", CashFlowEnum.unknown),
	recharge_diamond(1, "账户钻石充值", CashFlowEnum.recharge_diamond),
	pay_security_deposit(2, "缴纳信用承诺金", CashFlowEnum.security_deposit),
	pay_paid_appoint(3, "支付付费约订单", CashFlowEnum.pay_paid_appoint), 
	pay_paid_appoint_tip(4, "支付付费约小费", CashFlowEnum.pay_paid_appoint_tip),
	pay_appoint_site(5, "场地费用", CashFlowEnum.pay_appoint_site),
	pay_appoint_gold(6, "预约金", CashFlowEnum.pay_appoint_gold),
	pay_svip(7 , "开通高级会员" , CashFlowEnum.pay_svip),
	pay_dispatch(8, "派单" , CashFlowEnum.pay_dispatch),
	pay_paid_skill(9, "支付付技能订单", CashFlowEnum.pay_paid_skill), 
	pay_paid_skill_tip(10, "支付技能小费", CashFlowEnum.pay_paid_skill_tip),
	recharge_energy(11, "能量账户充值", CashFlowEnum.recharge_energy),
	recharge_red_flower(12, "小红花账户充值", CashFlowEnum.recharge_red_flower),
	recharge_guard(13, "守护充值", CashFlowEnum.recharge_guard),
	recharge_vip(14, "VIP充值", CashFlowEnum.recharge_vip),
	room_weeks_card(15, "音视频周卡", CashFlowEnum.room_weeks_card),
	dial_experience(16, "畅聊体验", CashFlowEnum.dial_experience),
	;

	//***常量值必须跟枚举名称一一对应(非常重要)***
	public static final String RECHARGE = "recharge_diamond";
	public static final String SECURITY = "pay_security_deposit";
	public static final String APPOINT = "pay_paid_appoint";
	public static final String TIP = "pay_paid_appoint_tip";
	public static final String SITE = "pay_appoint_site";
	public static final String GOLD = "pay_appoint_gold";
	public static final String SVIP = "pay_svip";
	public static final String DISPATCH = "pay_dispatch";
	public static final String SKILL = "pay_paid_skill";
	public static final String SKILLTIP = "pay_paid_skill_tip";
	public static final String ENERGY = "recharge_energy";
	public static final String REDFLOWER = "recharge_red_flower";
	public static final String GUARD = "recharge_guard";
	public static final String VIP = "recharge_vip";
	public static final String ROOM_WEEKS_CARD = "room_weeks_card";
	public static final String DIAL_EXPERIENCE = "dial_experience";

	private int code;
	private String desc;
	private CashFlowEnum cash;

	private static final Map<Integer, PayTypeEnum> pt = new HashMap<Integer, PayTypeEnum>();

	static {
		for (PayTypeEnum refer : PayTypeEnum.values()) {
			pt.put(refer.getCode(), refer);
		}
	}

	private PayTypeEnum(int code, String desc, CashFlowEnum cash) {
		this.code = code;
		this.desc = desc;
		this.cash = cash;
	}

	public static PayTypeEnum getByCode(int code) {
		PayTypeEnum errCode = pt.get(code);
		if (errCode == null) {
			return PayTypeEnum.unknown;
		}
		return errCode;
	}

	public static String getDescByCode(int code) {
		return getByCode(code).desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public CashFlowEnum getCash() {
		return cash;
	}

}
