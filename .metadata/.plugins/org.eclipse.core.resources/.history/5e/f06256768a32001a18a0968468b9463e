package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.IErrorCodeEnum;
import com.tigerjoys.shark.miai.Const;

/**
 * 返回的信息枚举
 * @author chengang
 *
 */
public enum ErrorCodeEnum implements IErrorCodeEnum {
	
	//枚举
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
	noSyncData(1010 , "没有要同步的数据"),
	pushSendError(1012,"推送失败"),
	user_not_found(1013 , "用户未找到"),
	bg_picture_empty_error(1018,"背景图片上传为空"),
	photo_empty_error(1019,"头像上传为空"),
	valid_login_user_error(1020 , "判断登录用户发生异常"),
	user_isnull(1021,"用户不存在"),
 	user_freeze(1022,"用户已被冻结"),
 	alreay_other_device(1023,"您的账号已在其它手机登录"),//此枚举一定不能删除或者更改code
	user_nickname_no_rule(1024,"昵称只允许2-10位，支持汉字、非纯数字、英文大小写、英文半角符号“-”和“_”，且不能以yoyo开头。"),
	wechat_prepay_return_fail(1025,"微信预支付响应失败"),
	pay_sign_check_fail(1026,"签名验签失败"),
	recharge_no_first(1027,"不是首次充值"),
	parameter_isnull(1028,"传入参数为空"),
	no_check_upgrade(1029,"当前已经是最新版本"),
	no_find_channel(1030,"没有找到相关的渠道信息"),
	no_find_package(1031,"没有找到相关的包名信息"),
	no_nickname(1032,"用户昵称为空"),
	privacy_passwor_no_rule(1033,"密码只允许6-15位、仅限数字和字母"),
	privacy_passwor_false(1034,"密码错误！"),
	sex_forbid_update(1035,"性别不可修改！"),
	extract_account_notenough(1036 , "提现金额超出账户余额"),
	extract_account_lower_bound(1037 , "提现金额不满足50元"),
	extract_account_over_times(1038 , "每周只能提现一次"),
	token_expire_error(1039 , "您的登录验证已过期，请重新登录"),
	user_nickname_exist(1040,"该用户名已被注册！"),
	no_upgrade_goldcoin_Error(1041 , "不能升级，所需金币数不够"),
	no_buy_paid_appoint_yourself(1043, "自己不能提交跟自己约会的订单"),
	balance_no_enough(1044 , "余额不足，请选择其他支付方式"),
	paid_appoint_evidence_pic_save_error(1045 , "付费约约会凭证上传发生异常"),
	iap_in_app_blank(1046,"in_app数组为空，请重新获取收据信息"),
	incomplete_information(1047 , "你填写的信息不完整"),
	already_promise_money(1048, "您已经缴纳过保证金了"),
	paid_coupon_error(1049 , "优惠券状态异常"),
	paid_credit_too_small(1050 , "您的信用值太低了"),
	paid_blacklist_error(1051 , "您已被服务者拉黑，无法进行购买"),
	user_mobile_authentication(1054 , "请进行联系方式认证"),//请不要修改
	paid_profile_not_perfect(1055 , "达人资料还未完善"),
	paid_appoint_aleady_talent(1056 , "您已经是达人了"),
	user_chat_not_able(1057,"VIP用户\n获得无限畅聊权益"),
	user_withdrawal_limit(1058, "提现限额:" + Const.WITHDRAWAL_LIMIT + "元"),
	paid_sign_distance_not_enough(1059 , "签到距离大于" + Const.PAID_APPOINT_SIGN_DISTANCE_METRE + "米"),
	photo_upload_null_error(1061,"上传相册图片不能为空"),
	photo_upload_error(1062,"上传相册失败"),
	age_less_than_18(1063,"您选择的年龄不能小于18岁"),
	video_upload_error(1071,"视频上传失败"),
	attention_friends_error(1081,"自己不能关注自己"),
	auth_mobile_exist(1082,"此手机号已被绑定！"),
	share_invite_name_exist(1083,"此名称已被使用！"),
	share_invite_code_isnull(1084,"邀请码已用完！"),
	stick_user_diamond_not_enough(1085,"用户置顶钻石余额不足！"),
	free_appoint_waiting_response(1086,"等待对方回应，无法再次申请"),
	free_appoint_reject(1087,"对方已拒绝，无法再次申请"),
	honesty_badge_not_expire(1088,"此诚信徽章还未到期！"),
	look_user_contacts_limit_full(1089,"您已经查看了3个，请明天再试！"),
	user_diamond_not_enough(21009,"您的钻石余额不足！"),
	user_handler_not_again_complaints(1091,"投诉已处理，不能再次投诉"),
	order_status_error_not_complaints(1092,"订单状态已更改，此状态不能投诉"),
	user_not_badge_not_complaints(1093,"用户没有诚信徽章，不能进行投诉"),
	free_order_offline(1094,"此约会已下架"),
	free_order_no_exist(1095,"约会定单不存在"),
	free_order_user_no_permissions(1096,"没权限操作别人的约会定单"),
	order_status_error_not_operation(1097,"订单状态已更改，此状态不能操作"),
	free_blacklist_error(1098 , "您已被对方拉黑，无法操作！"),
	user_no_proxy(1099 , "您不是代理人用户，无法操作"),
	task_withdrawal_no_permission(1100, "禁止单独提现任务收益"),
	task_withdrawal_limit(1101, "任务提现限额:" + Const.TASK_WITHDRAWAL_LIMIT + "元"),
	task_withdrawal_date_limit(1102, "任务提现时间间隔至少:" + Const.TASK_WITHDRAWAL_DATE_LIMIT + "天"),
	user_intro_no_rule(2010,"个人介绍只允许2-15位，支持汉字、非纯数字、英文大小写、英文半角符号“-”和“_”"),
	user_signature_no_rule(2011,"个性签名只允许2-15位，支持汉字、非纯数字、英文大小写、英文半角符号“-”和“_”"),
	paid_appoint_status_error(10103,"业务状态异常，可能此约会已下架"),
	user_type_feedback_null(10104,"反馈类型不能为空"),
	paid_skill_status_error(10105,"业务状态异常，可能此技能已下架"),
	paid_skill_no_type_error(10106,"技能类型不能为空"),
	paid_skill_no_title_error(10107,"技能标题不能为空"),
	paid_skill_no_price_error(10108,"价格不能为空"),
	paid_skill_no_startHours_error(10109,"起约时长不能为空"),
	paid_skill_no_description_error(10110,"技能描述不能为空"),
	paid_skill_no_picture_error(10111,"封面不能为空"),
	setting_dispatch_need_one_error(10200,"至少选择一个技能类型"),
	commend_need_face_value(10300,"对应的颜值不能为空"),
	talent_expire_error(10301 , "您的达人vip已经过期,不能保存技能信息"),
	pay_balance_error(10302,"请支付尾款"),
	repeat_dispatch_order_error(10303,"派单重复"),
	dispatch_timeout_error(10304,"派单超时"),
	share_vip_moblie_error(10305,"请填写正确的手机号"),
	share_vip_invite_error(10306,"请填写正确的邀请码"),
	income_pay_not_enough(10307, "收益抵扣额不足"),
	hundredResponses_diamond_not_enough(10400,"钻石余额不足！请充值"),
	appointment_already_Vote(10308,"您已投注，不可再次投注"),
	appointment_already_Vote_full(10309,"投注已满，不可再投注"),
	anchor_calling_video_chat(10310,"对方正在通话，请稍后再拔"),
	anchor_unOnline(10311,"对方离线状态，不可以拔打"),
	short_video_offline(10312,"短视频已下架"),
	dialing_slef_error(10313,"自己不能拔打自己！"),
	anchor_inexistence(10314,"用户数据异常"),
	h5_pay_query_time(10400,"H5支付查询中"),
	h5_pay_query_timeout(10401,"H5支付查询超时"),
	sms_recode_Error(10402,"验证码不能为空"),
	user_calling_video_chat(10403,"正在通话，请稍后再拔"),
	
	auth_mobile_new(10500,"请使用注册的手机号绑定！"),
	
	audio_upload_error(10600,"音频上传失败"),
	audio_control_error(10601,"操作音频文件出现问题"),
	audio_delete_error(10602,"不能删除选中状态的音频文件"),
	anchor_star_flower_not_enough(10800,"余额不足，请购买小红花"),
	anchor_star_time_not_enough(10801,"开奖倒计时，无法投票"),
	anchor_star_error(10802,"投票出现了问题"),
	anchor_star_flower_error(10803,"请输入正确的小红花数量"),
	anchor_star_flower_vote_error(10804,"最小投入为5朵小红花"),
	user_dial_check_error(10805,"请先调用通话前效验接口"),
	root_user_reg_login(10806,"系统升级中,请稍后再试！"),
	point_exchang_rule_false(10807,"请输入的积分为100的倍数"),
	point_exchang_more_balance(10808,"积分余额不足"),
	user_nickname_check_refuse(10809,"用户名内容违规,请重新输入!"),
	only_V_can_login(10810,"您还不是大V，无法登录"),
	anchor_send_msg_to_anchor(10811,"暂无权限发送消息"),
	buy_guard_anchorId_is_null(10812,"购买守护主播不能为空"),
	only_user_can_login(10813,"请使用大V助手APP登陆"),
	user_already_logout(10814,"该账号已被注销，请重新注册账号，或者180天后再试"),
	
	web_anchor_subscribe_diamond_not_enough(10900,"预约金不足"),
	web_anchor_subscribe_anchor_not_enough(10901,"没有可以预约的主播"),
	web_anchor_subscribe_exist(10902,"已经进行过预约了"),
	web_anchor_subscribe_error(10903,"一键预约出现了问题"),
	audio_msg_nonexistence(10904,"本语音消息不存在"),
	modify_mobile_same(10905,"与修改的手机号相同"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , ErrorCodeEnum> ERR_DESC = new HashMap<Integer , ErrorCodeEnum>();
	
	static {
		for(ErrorCodeEnum refer : ErrorCodeEnum.values()) {
			ERR_DESC.put(refer.getCode(), refer);
		}
	}
	
	private ErrorCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static ErrorCodeEnum getByCode(int code) {
		ErrorCodeEnum errCode = ERR_DESC.get(code);
		if(errCode == null) {
			errCode = unkonwn;
		}
		
		return errCode;
	}
	
	public static String getDescByCode(int code) {
		return getByCode(code).desc;
	}
	
	@Override
	public int getCode() {
		return code;
	}
	
	@Override
	public String getDesc() {
		return desc;
	}
}
