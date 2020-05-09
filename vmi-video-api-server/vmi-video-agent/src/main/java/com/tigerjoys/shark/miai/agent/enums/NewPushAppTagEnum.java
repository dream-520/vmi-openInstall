package com.tigerjoys.shark.miai.agent.enums;


/**
 * app页参数类
 * @author liuman
 */
public enum NewPushAppTagEnum {
	//TODO 
//	attend_user("attendUser",1,1,2,"MyFriendFragment", "", "关注用户"),
	/**
	 * h5消息详情页
	 */
	sysmessage_H5_fragment("sysmessage",1,1,2,"WebSingleSaveFragment", "8", "系统消息推送","","",0),
	common_message_H5_fragment("commonMessage",2,1,2,"WebSingleSaveFragment","8","通用消息推送","","",0),
	/**
	 * 跳转到业务消息列表
	 */
	bussiness_msg_fragment("bussinessMsg",3,1,2,"BusMsgFragment","1","业务消息推送","","",0),
	//需求未定义
	examine_publish_service_sucess("examinePublishService",4,1,2,"BusMsgFragment","1","审核发布服务成功消息推送","审核发布服务消息","审核发布服务消息成功",0),
	//后台审核投诉相关
	successful_complaint("successfulComplaint",5,1,2,"BusMsgFragment","1","投诉成功消息推送","投诉消息","投诉成功",0),
	failed_complaint("failedComplaint",6,1,2,"BusMsgFragment","1","投诉失败消息推送","投诉消息","投诉失败",0),
	
	//后台审核是否成为达人(服务者)
	become_servicer_success("becomeServicerSuccess",7,1,2,"BusMsgFragment","1","同意成为达人消息推送","达人审核消息","达人审核通过",0),
	//认证失败,要后台调用自己设置原因
	become_servicer_fail("becomeServicerFail",8,1,2,"BusMsgFragment","1","同意成为达人消息推送","达人审核消息","达人审核失败",0),
	
	//后台审核用户认证是否通过
	//certification_success("certificationSuccess",9,1,2,"BusMsgFragment","1","认证审核通过消息推送","认证审核消息","认证审核通过"),
	//认证失败,要后台调用自己设置原因
	//certification_fail("certificationFail",10,1,2,"BusMsgFragment","1","认证审核未通过消息推送","认证审核消息","认证审核失败"),
	
	//后台审核用户认证是否通过
	certification_success("certificationSuccess",9,1,2,"BusMsgFragment","1","认证大V通过消息推送","认证审核消息","恭喜你申请大V已通过",0),
	//认证失败,要后台调用自己设置原因
	certification_fail("certificationFail",10,1,2,"BusMsgFragment","1","认证大V未通过消息推送","认证审核消息","申请大V未通过，请重新申请",0),
	
	
	//后台达人管理(查封或者恢复达人身份)
	enable_servicer_vip("enableServicerVip",11,1,2,"BusMsgFragment","1","恢复达人vip功能的服务消息推送","达人管理消息","您的达人身份已恢复",0),
	//达人身份被禁止,要后台调用自己设置内容
	disable_servicer_vip("disableServicerVip",12,1,2,"BusMsgFragment","1","禁止达人vip功能的服务消息推送","达人管理消息","您的达人身份已禁止",0),
	/**
	 * 跳转到聊天页面 对方昵称和消息内容需要填写
	 */
	chat("chat",13,1,2,"ChatSingleFragment","4","聊天消息推送","","",0),
	
	/**
	 * 我发布的付费(订单模块)
	 */
	someone_apply_your_service("applyYourService",14,1,2,"ServiceOrderOwn","2","有人申请了你的约会消息推送","订单消息","有人申请了你的约会",0),
	cancel_apply("cancelOrder",15,1,2,"ServiceOrderOwn","2","对方已撤销了申请消息推送","订单消息","对方已撤销了申请",0),
	
	/**
	 * 我购买的付费(订单模块)
	 */
	unconfirmed("unconfirmed",16,1,2,"ServiceOrderBuy","3","未确认消息推送","订单消息","未确认扣信用分3分",0),
	apply_sucess("applySuccess",17,1,2,"ServiceOrderBuy","3","同意约会请求消息推送","订单消息","对方同意了你的约会申请",0),
	apply_fail("applyFail",18,1,2,"ServiceOrderBuy","3","拒绝约会请求消息推送","订单消息","对方拒绝了你的约会申请",0),
	cancle_your_apply("cancleYourApply",19,1,2,"ServiceOrderBuy","3","对方已撤销你的申请消息推送","订单消息","对方已撤销你的申请",0),
	has_signed("hasSigned",20,1,2,"ServiceOrderBuy","3","对方已签到,请及时确认消息推送","订单消息","对方已签到,请及时确认见面",0),
	
	/**
	 * 跳转到我发布的和我购买的(订单模块),会向约会双方都发送通知
	 */
	order_timeout("orderTimeout",21,1,2,"ServiceOrderOwn","2","订单超时未处理消息推送","订单消息","订单超时未处理,约会已取消",0),
	appointment_begin_soon("appointmentBeginSoon",22,1,2,"ServiceOrderOwn","2","约会马上开始消息推送","订单消息","约会马上开始,请合理安排时间",0),
	
	/**
	 * 之前需求漏掉的信息
	 */
	examine_publish_service_fail("examinePublishService",23,1,2,"BusMsgFragment","1","审核发布服务消息推送","审核发布服务消息","审核发布服务消息失败",0),
	has_been_complaint("hasBeenComplaint",24,1,2,"BusMsgFragment","1","被投诉消息推送","投诉消息","您被投诉了",0),
	no_signed("noSigned",25,1,2,"ServiceOrderOwn","2","未签到消息推送","订单消息","未签到,扣除信用分3分",0),
	personal_page("personalPage",26,1,2,"HomepageFragment","6","个人消息推送","个人消息","个人消息",0),
	
	/**
	 * 我发布的免费(订单模块)
	 */
	someone_apply_your_service_free("applyYourService",27,1,2,"EgtReceivedFragment","11","有人申请了你的约会消息推送","订单消息","有人申请了你的约会",0),
	cancel_apply_free("cancelOrder",28,1,2,"EgtReceivedFragment","11","对方已撤销了申请消息推送","订单消息","对方已撤销了申请",0),
	order_timeout_my_posted_free("YourApplyTimeout",29,1,2,"EgtReceivedFragment","11","超时未处理","订单消息","超时未处理",0),
	
	/**
	 * 我购买的免费(订单模块)
	 */
	apply_sucess_free("applySuccess",30,1,2,"EgtApplyforFragment","12","同意约会请求消息推送","订单消息","对方同意了你的约会申请",0),
	apply_fail_free("applyFail",31,1,2,"EgtApplyforFragment","12","拒绝约会请求消息推送","订单消息","对方拒绝了你的约会申请",0),
	order_timeout_my_apply_free("YourApplyTimeout",32,1,2,"EgtApplyforFragment","12","超时未处理","订单消息","超时未处理",0),
	
	/**
	 * 达人接收派单信息
	 */
	master_receive_dispatch("masterReceiveDispatch",33,1,2,"OrderMgrFragment","13","新的约会订单","接单消息","新的约会订单",0),
	
	/**
	 * 充值提醒
	 */
	recharge_user("masterReceiveDispatch",34,1,2,"PushRichUserDlg","20","用户充值提醒","用户充值提醒","用户充值提醒",1),
	
	/**
	 * 用户唤醒
	 */
	wakeup_user("wakeupUser",35,1,2,null,"21","用户唤醒","打开APP消息","打开APP消息",0),
	
	/**
	 * 用户上线 唤醒主播通知栏消息
	 */
	user_wakeup_anchor("userWakeupAnchor",36,1,2,"RichUserListFragment","22"," 唤醒主播通知栏消息"," 唤醒主播通知栏消息"," 唤醒主播通知栏消息",0),
	
	/**
	 * 用户唤醒
	 */
	user_head_apply_fail("userHeadApplyFail",37,1,2,null,"23","头像审核结果","审核失败","您好，您的头像不符合要求，请重新上传头像。",0),
	
	/**
	 * 用户唤醒
	 */
	user_photo_apply_fail("userPhotoApplyFail",38,1,2,null,"24","相册审核结果","审核失败","您好，您上传的相册图片不符合要求，请重新上传。",0),
	
	/**
	 * 用户唤醒
	 */
	user_video_apply_fail("userVideoApplyFail",39,1,2,null,"25","短视频审核结果","审核失败","您好，您上传的视频不符合要求，请重新上传。",0),
	
	/**
	 * 用户上线 唤醒主播通知栏消息
	 */
	user_subscribe_anchor("userSubscribeAnchor",40,1,2,"AnchorSubscribeListFragment","26", "用户预约主播", "用户预约主播", "用户预约主播息", 0),
	;
	
	/**
	 * 页面功能代码
	 */
	private String business;
	
	/**
	 * 类型代码
	 */
	private int type;
	
	/**
	 * 安卓
	 */
	private int android;
	/**
	 * ios
	 */
	private int ios;
	
	/**
	 * android页面代码
	 */
	private String androidCode;
	
	/**
	 * ios页面代码
	 */
	private String iosCode;
	
	/**
	 * 功能描述
	 */
	private String desc;
	
	/**
	 * 推送消息标题
	 */
	private String messageTitle;
	
	/**
	 * 推送消息标题
	 */
	private String messageContent;
	
	private int ios_control;

	private NewPushAppTagEnum(String business, int type, int android, int ios,String androidCode,String iosCode,String desc,String messageTitle,String messageContent, int ios_control) {
		this.business = business;
		this.type = type;
		this.android = android;
		this.ios = ios;
		this.androidCode = androidCode;
		this.iosCode = iosCode;
		this.desc = desc;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.ios_control = ios_control;
	}

	public static String getDescByCode(String code) {
		if(code == null) return null;
		for (NewPushAppTagEnum refer : NewPushAppTagEnum.values())
			if (code.equals(refer.getBusiness()))
				return refer.getDesc();
		return null;
	}


	public String getDesc() {
		return desc;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAndroid() {
		return android;
	}

	public void setAndroid(int android) {
		this.android = android;
	}

	public int getIos() {
		return ios;
	}

	public void setIos(int ios) {
		this.ios = ios;
	}

	public String getAndroidCode() {
		return androidCode;
	}

	public void setAndroidCode(String androidCode) {
		this.androidCode = androidCode;
	}

	public String getIosCode() {
		return iosCode;
	}

	public void setIosCode(String iosCode) {
		this.iosCode = iosCode;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getIos_control() {
		return ios_control;
	}

	public void setIos_control(int ios_control) {
		this.ios_control = ios_control;
	}
	
}
