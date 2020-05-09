package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信用记录枚举类
 * @author liuman
 *
 */
public enum UserCreditRecordEnum {
	
	unkonwn(0,"未知"),
	commentAdd(1,"好评加分"),
	purchaseAdd(2,"购买加分"),
	paidAppointLater(3,"约会迟到减分"),
	paidAppointBuyerComplaint(4 , "买家投诉减分"),
	paidAppointSellerComplaint(5 , "卖家投诉减分"),
	paidAppointBuyerCancel(6, "买家撤销订单减分"),
	paidAppointSellerLate(7 , "卖家迟到减分"),
	paidAppointSellerNoSign(8 , "卖家未签到减分"),
	paidAppointSellerCancel(9 , "卖家撤销订单减分"),
	paidAppointBuyerWasCommendIncrease(10 , "卖家评价加分"),
	paidAppointBuyerWasCommendDecrease(11 , "卖家评价减分"),
	paidAppointSellerWasCommendIncrease(12 , "买家评价加分"),
	paidAppointSellerWasCommendDecrease(13 , "买家评价减分"),
	initAdd(14,"初始化信用账户"),
	auditAppoint(15 , "审核通过付费约信息"),
	paidAppointBuyerNoConfirmMeetDecrease(16 , "未确认见面减分"),
	appointComplaint(17 , "普通约投诉减分"),
	//TODO 待增加
 	;
	
	/**
	 * 用户类型代码
	 */
	private final int code;
	
	/**
	 * 用户类型描述
	 */
	private final String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(UserCreditRecordEnum refer : UserCreditRecordEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserCreditRecordEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		String desc = code_desc.get(code);
		if(desc == null) {
			desc = unkonwn.desc;
		}
		return desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
