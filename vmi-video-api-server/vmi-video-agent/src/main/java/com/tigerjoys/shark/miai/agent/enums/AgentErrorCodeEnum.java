package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.IErrorCodeEnum;

/**
 * 返回的信息枚举
 * @author chengang
 *
 */
public enum AgentErrorCodeEnum implements IErrorCodeEnum {
	
	success(0,"成功"),
	unkonwn(1000,"未知错误"),
	error(1001,"系统异常"),
	sign_error(1002,"非法数据"),
	parameter_error(1003,"参数校验失败"),
 	db_error(1004,"数据异常"),
 	db_not_found(1005,"没有找到数据，可能不存在或已被删除"),
 	have_no_permission(1006,"你没有权限进行操作"),
 	state_error(1007,"业务状态异常"),
	runError(1008,"运行时抛出异常"),
	haveBeCall(1009,"接口已调用"),
	wechat_prepay_return_fail(1025,"微信预支付响应失败"),
	pay_sign_check_fail(1026,"签名验签失败"),
	iap_in_app_blank(1046,"[in_app]数组为空，请重新获取收据信息"),
	diamond_not_enough(21009,"您的钻石余额不足"),//不要更改，客户端写死
	not_enough(21010,"余额不足"),//不要更改
	repeate_record(21011,"记录重复 "),
	paidappoint_status_error(21043 , "状态异常，不能进行操作"),
	paidappoint_submit_evidence(21044 , "您已提交过了约会凭证"),
	paidappoint_commend(21045 , "您已经评价过了"),
	paidappoint_commend_status_error(21046 , "约会当前的状态无法进行评价"),
	paidappoint_buyer_report_status_error(21047 , "约会当前的状态无法进行投诉"),
	paidappoint_seller_report_status_error(21048 , "约会当前的状态无法进行投诉"),
	paidappoint_cancel_status_error(21049 , "约会当前的状态无法进行撤销"),
	paidappoint_not_found(21050 , "付费约信息未找到"),
	paidappoint_seller_cancel_timeend(21051 , "你只能在约会开始前取消订单"),
	paidappoint_not_submit_evidence(21052 , "您还没有上传约会凭证"),
	mobile_error(21053 , "手机号码不正确"),
	msgSendError(21054,"短信发送失败"),
	send_code_out(21055 , "今日验证码已达上限！"),
	valid_code_error(21056 , "验证码错误"),
	valid_code_pass(21057 , "验证码超时"),
	task_not_in_time(21058 , "任务不在有效可完成时间内"),
	task_over_out_times(21059 , "超过当前任务可完成次数"),
	task_chat_repeatable(21060 , "重复跟同一用户聊天"),
	user_username_isused(21061 , "用户名已被使用"),
	user_withdrawal_limit_times(21062 , "每日只能够提现一次，您已经提现过了"),
	user_withdrawal_saturday_and_sunday(21063 , "每周六日可提现"),
	vchat_orderid_no_exist(21064 , "通话订单不存在"),
	gift_vip_no_send(21065 , "您还不是vip，无法发送此礼物"),
	IPA_product_id_error(21066 , "IPA支付产品号错误"),
	IPA_transaction_id_error(21067 , "IPA支付流水号不存在"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , AgentErrorCodeEnum> err_desc = new HashMap<Integer , AgentErrorCodeEnum>();
	
	static {
		for(AgentErrorCodeEnum refer : AgentErrorCodeEnum.values()) {
			err_desc.put(refer.getCode(), refer);
		}
	}
	
	private AgentErrorCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static AgentErrorCodeEnum getByCode(int code) {
		AgentErrorCodeEnum errCode = err_desc.get(code);
		if(errCode == null) errCode = unkonwn;
		
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

}
