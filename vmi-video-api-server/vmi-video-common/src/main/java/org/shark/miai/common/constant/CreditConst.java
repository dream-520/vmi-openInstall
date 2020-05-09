package org.shark.miai.common.constant;

/**
 * 信用分常量
 * @author chengang
 *
 */
public final class CreditConst {
	
	/**
	 * 付费约购买方取消同意的订单扣除信用分
	 */
	public static final int PAID_APPOINT_BUYTER_AGREE_CANCEL_CREDIT = 3;
	
	/**
	 * 付费约购买方取消约会开始后的订单扣除信用分
	 */
	public static final int PAID_APPOINT_BUYER_EXCEED_TIME_CANCEL_CREDIT = 5;
	
	/**
	 * 买家未确认见面扣除信用分
	 */
	public static final int PAID_APPOINT_BUYER_NO_CONFIRM_MEET_CREDIT = 3;
	
	/**
	 * 付费约服务方签到迟到
	 */
	public static final int PAID_APPOINT_SELLER_LATE_CREDIT = 3;
	
	/**
	 * 付费约服务方取消约会开始的订单扣除信用分
	 */
	public static final int PAID_APPOINT_SELLER_EXCEET_TIME_CANCEL_AGREE_CREDIT = 5;
	
	/**
	 * 买家未签到，自动关闭订单，扣除的信用分
	 */
	public static final int PAID_APPOINT_SELLER_NO_SIGN_CREDIT = 3;
	
	/**
	 * 付费约评分增减信用的索引，负数是扣除分数，5是最高分
	 */
	public static final int[] PAID_APPOINT_COMMEND_CREDIT_ARRAY = new int[]{0,-3,-2,-1,1,2};

}
