/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: UserDiamondEnum <br/> 
 * date: 2017年5月10日 下午5:26:34 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum CashFlowEnum {
	unknown(0, "未知"),
	recharge_diamond(1,"充值"),
	withdrawal(2,"提现"),
	security_deposit(3 , "信用承诺金"),
	pay_paid_appoint(4 , "支付付费约订单"),
	pay_paid_appoint_tip(5 , "支付付费约小费"),
	pay_appoint_site(6 , "支付场地订单"),
	pay_appoint_gold(7 , "支预约金"),
	pay_svip(8 , "开通高级会员"),
	pay_dispatch(9 , "派单约会"),
	pay_paid_skill(10 , "支付技能订单"),
	pay_paid_skill_tip(11 , "支付技能小费"),
	recharge_energy(12, "充值能量"),
	recharge_red_flower(13, "充值小红花"),
	recharge_guard(14, "充值守护"),
	recharge_vip(15, "充值VIP"),
	room_weeks_card(16, "音视频周卡"),
	dial_experience(17, "畅聊体验"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(CashFlowEnum refer : CashFlowEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private CashFlowEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
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
