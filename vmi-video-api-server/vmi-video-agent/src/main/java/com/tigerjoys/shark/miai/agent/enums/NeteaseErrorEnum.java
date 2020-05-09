/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: NeteaseErrorEnum <br/> 
 * date: 2017年5月2日 下午3:32:22 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum NeteaseErrorEnum {

	success(200,"操作成功"),
	client_version_error(201,"客户端版本不对，需升级sdk"),
	sealing_off(301,"被封禁"),
	user_no_pass(302,"用户名或密码错误"),
	ip_no_pass(315,"IP限制"),
	operation_illegal(403,"非法操作或没有权限"),
	no_existed(404,"对象不存在"),
	too_long(405,"参数长度过长"),
	read_only(406,"对象只读"),
	time_out(408,"客户端请求超时"),
	sms_no_validate(413,"验证失败(短信服务)"),
	param_error(414,"参数错误"),
	client_net_error(415,"客户端网络问题"),
	flip_control(416,"频率控制"),
	operation_repeated(417,"重复操作"),
	channel_no_use(418,"通道不可用(短信服务)"),
	over_amount(419,"数量超过上限"),
	account_sealing_off(422,"账号被禁用"),
	http_repeated(431,"HTTP重复请求"),
	server_inner_error(500,"服务器内部错误"),
	server_busy(503,"服务器繁忙"),
	undo_time_out(508,"消息撤回时间超限"),
	protocol_invalid(509,"无效协议"),
	service_no_use(514,"服务不可用"),
	expand_package_error(998,"解包错误"),
	pick_package_error(999,"打包错误"),
	//群相关错误码
	group_over_member(801,"群人数达到上限"),
	privilege_limit(802,"没有权限"),
	group_no_exsited(803,"群不存在"),
	user_noin_group(804,"用户不在群"),
	group_type_error(805,"群类型不匹配"),
	group_over_amount(806,"创建群数量达到限制"),
	group_member_error(807,"群成员状态错误"),
	apply_success(808,"申请成功"),
	has_in_group(809,"已经在群内"),
	invite_success(810,"邀请成功"),
	//音视频、白板通话相关错误码
	channel_invalid(9102,"通道失效"),
	has_response(9103,"已经在他端对这个呼叫响应过了"),
	chat_no_reach(11001,"通话不可达，对方离线状态"),
	//聊天室相关错误码
	im_connect_error(13001,"IM主连接状态异常"),
	room_status_error(13002,"聊天室状态异常"),
	black_list_limit(13003,"账号在黑名单中,不允许进入聊天室"),
	say_no_permit(13004,"在禁言列表中,不允许发言"),
	//特定业务相关错误码
	email_error(10431,"输入email不是邮箱"),
	mobile_error(10432,"输入mobile不是手机号码"),
	passwd_match_error(10433,"注册输入的两次密码不相同"),
	company_no_exsited(10434,"企业不存在"),
	account_no_pass(10435,"登陆密码或帐号不对"),
	app_no_exsited(10436,"app不存在"),
	email_has_registed(10437,"email已注册"),
	mobile_has_registed(10438,"手机号已注册"),
	app_name_existed(10441,"app名字已经存在"),
	
	
	//返垃圾回调
	notify_safe_msg_success(0,"下发消息"),
	notify_safe_msg_err(1,"不下发消息"),
	;

	private int code;
	private String desc;
	
	private static final Map<Integer , String> cache = new HashMap<Integer , String>();
	
	static {
		for(NeteaseErrorEnum refer : NeteaseErrorEnum.values()) {
			cache.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private NeteaseErrorEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return cache.get(code);
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
