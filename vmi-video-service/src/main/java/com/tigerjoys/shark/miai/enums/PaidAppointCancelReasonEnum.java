package com.tigerjoys.shark.miai.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 付费约订单撤销理由枚举
 * @author chengang
 *
 */
public enum PaidAppointCancelReasonEnum {
	//
	buyer_1("计划有变" , 1),
	buyer_2("不想去了" , 1),
	buyer_3("有别的约会" , 1),
	////////////////分割线/////////////////////////////
	seller_1("计划有变" , 2),
	seller_2("不想去了" , 2),
	seller_3("有别的约会" , 2),
	;
	
	private static final List<String> BUYER_CANCEL_REASON_LIST;
	private static final List<String> SELLER_CANCEL_REASON_LIST;
	
	static {
		List<String> buyerCancelReasonList = new ArrayList<>();
		List<String> sellerCancelReasonList = new ArrayList<>();
		for(PaidAppointCancelReasonEnum cm : PaidAppointCancelReasonEnum.values()) {
			if(cm.type == 1) {
				buyerCancelReasonList.add(cm.reason);
			} else {
				sellerCancelReasonList.add(cm.reason);
			}
		}
		
		BUYER_CANCEL_REASON_LIST = Collections.unmodifiableList(buyerCancelReasonList);
		SELLER_CANCEL_REASON_LIST = Collections.unmodifiableList(sellerCancelReasonList);
	}
	
	private final String reason;
	
	/**
	 * 1买家,2卖家
	 */
	private final int type;
	
	PaidAppointCancelReasonEnum(String reason , int type) {
		this.reason = reason;
		this.type = type;
	}
	
	/**
	 * 返回购买者撤销理由列表
	 * @return List<String>
	 */
	public static List<String> getBuyerCancelReasonList(){
		return BUYER_CANCEL_REASON_LIST;
	}
	
	/**
	 * 返回服务者撤销理由列表
	 * @return List<String>
	 */
	public static List<String> getSellerCancelReasonList(){
		return SELLER_CANCEL_REASON_LIST;
	}

	public String getReason() {
		return reason;
	}

	public int getType() {
		return type;
	}

}
