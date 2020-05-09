package org.shark.miai.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务消息类型枚举
 * @author liuman
 *
 */
public enum BussinessMessageTypeEnum {
	/**
	 * 为了数据库查询条件设置
	 */
	//type, code, title, intro,openurl, opentype,hitType,androidAppPage,iosAppPage
	//最新发生的业务消息
	my_latest_message(0,"latest_message","我最新发生的业务消息的","最新发生的业务消息","test.com",1,1,"ServiceOrderOwn","2"),
	//我发布的付费约
	my_publish(1,"my_publish","我发布的","发布了新的约会服务","test.com",1,1,"ServiceOrderOwn","2"),
	//我购买的付费约
	my_purchase(2,"my_purchase","我购买的","为新的约会付费了","test.com",1,1,"ServiceOrderBuy","3"),
	//其他业务消息
	other_bussiness(3,"other_bussiness","业务消息","为新的约会付费了","test.com",1,0,"",""),
	//我发布的免费约
	my_publish_free(4,"my_publish","我发布的","发布了新的约会服务","test.com",1,1,"EgtReceivedFragment","11"),
	//我购买的免费约
	my_purchase_free(5,"my_purchase","我购买的","为新的约会付费了","test.com",1,1,"EgtApplyforFragment","12"),
	
	/**
	 * 我发布的付费约(订单模块)
	 */
	someone_apply_your_service(1,"applyYourService","订单消息","有人申请了你的服务","test.com",1,1,"ServiceOrderOwn","2"),
	cancel_apply(1,"cancelOrder","订单消息","对方已撤销了申请","test.com",1,1,"ServiceOrderOwn","2"),
	order_timeout_my_publish(1,"orderTimeout","订单消息","订单超时未处理,约会已取消","test.com",1,1,"ServiceOrderOwn","2"),
	appointment_begin_soon_my_publish(1,"appointmentBeginSoon","订单消息","约会马上开始,请合理安排时间","test.com",1,1,"ServiceOrderOwn","2"),
	no_signed_my_publish(1,"noSigned","订单消息","超时未签到，约会已关闭，扣除信用分3分。","test.com",1,1,"ServiceOrderOwn","2"),
	
	/**
	 * 我购买的付费约(订单模块)
	 */
	apply_sucess(2,"applySuccess","订单消息","对方同意了你的约会申请","test.com",1,1,"ServiceOrderBuy","3"),
	apply_fail(2,"applyFail","订单消息","对方拒绝了你的约会申请","test.com",1,1,"ServiceOrderBuy","3"),
	cancle_your_apply(2,"cancleYourApply","订单消息","对方已撤销你的申请","test.com",1,1,"ServiceOrderBuy","3"),
	has_signed(2,"hasSigned","订单消息","对方已签到,请及时确认见面","test.com",1,1,"ServiceOrderBuy","3"),
	order_timeout_my_purchase(2,"orderTimeout","订单消息","订单超时未处理,约会已取消","test.com",1,1,"ServiceOrderBuy","3"),
	appointment_begin_soon_my_purchase(2,"appointmentBeginSoon","订单消息","约会马上开始,请合理安排时间","test.com",1,1,"ServiceOrderBuy","3"),
	no_signed_my_purchase(2,"noSigned","订单消息","对方超时未签到，订单已关闭。","test.com",1,1,"ServiceOrderBuy","3"),
	unconfirmed(2,"unconfirmed","订单消息","未确认扣信用分3分","test.com",1,1,"ServiceOrderBuy","3"),
	
	/**
	 * 业务消息列表
	 */
	//审核发布服务相关
	examine_publish_service_sucess(3,"examinePublishService","审核发布服务消息","审核发布服务消息成功","test.com",1,0,"",""),
	examine_publish_service_fail(3,"examinePublishService","审核发布服务消息","审核发布服务消息失败","test.com",1,0,"",""),
	//后台审核投诉相关
	successful_complaint(3,"successfulComplaint","投诉消息","投诉成功","test.com",1,0,"",""),
	failed_complaint(3,"failedComplaint","投诉消息","投诉失败","test.com",1,0,"",""),
	has_been_complaint(3,"hasbeenComplaint","投诉消息","您被投诉了","test.com",1,0,"",""),
	//后台审核是否成为达人(服务者)
	become_servicer_success(3,"disableServicerVip","达人审核消息","达人审核通过","test.com",1,0,"",""),
	//认证失败,要后台调用自己设置原因
	become_servicer_fail(3,"disableServicerVip","达人审核消息","达人审核失败","test.com",1,0,"",""),
	//后台审核用户认证是否通过
	//certification_success(3,"disableServicerVip","认证审核消息","认证审核通过","test.com",1,0,"",""),
	//认证失败,要后台调用自己设置原因
	//certification_fail(3,"disableServicerVip","认证审核消息","认证审核失败","test.com",1,0,"",""),
	
	//后台审核用户认证是否通过
	certification_success(3,"disableServicerVip","大V审核消息","大V审核通过","test.com",1,0,"",""),
	//认证失败,要后台调用自己设置原因
	certification_fail(3,"disableServicerVip","大V审核消息","大V审核失败","test.com",1,0,"",""),
	
	//后台达人管理(查封或者恢复达人身份)
	enable_servicer_vip(3,"enableServicerVip","达人管理消息","您的达人身份已恢复","test.com",1,0,"",""),
	//达人身份被禁止,要后台调用自己设置内容
	disable_servicer_vip(3,"disableServicerVip","达人管理消息","您的达人身份已禁止","test.com",1,0,"",""),
	
	/**
	 * 我发布的免费(订单模块)
	 */
	someone_apply_your_service_free(4,"applyYourService","订单消息","有人申请了你的服务","test.com",1,1,"EgtReceivedFragment","11"),
	cancel_apply_free(4,"cancelOrder","订单消息","对方已取消了申请","test.com",1,1,"EgtReceivedFragment","11"),
	order_timeout_my_posted_free(4,"orderTimeout","订单消息","订单超时未处理,约会已取消","test.com",1,1,"EgtReceivedFragment","11"),
	
	/**
	 * 我购买的免费(订单模块)
	 */
	apply_sucess_free(5,"applySuccess","订单消息","对方同意了你的约会申请","test.com",1,1,"EgtApplyforFragment","12"),
	apply_fail_free(5,"applyFail","订单消息","对方拒绝了你的约会申请","test.com",1,1,"EgtApplyforFragment","12"),
	order_timeout_my_apply_free(5,"orderTimeout","订单消息","订单超时未处理,约会已取消","test.com",1,1,"EgtApplyforFragment","12"),
	//TODO 有新的业务就加入新的枚举名
	
	/**
	 * scvc 通知消息
	 */
	scvc_award_daily_online(6,"scvcOnline","在线时长奖励","今天你很活跃哦，奖励你10个SCVC积分。","test.com",1,0,"",""),
	scvc_award_daily_recharge(6,"scvcRecharge","充值奖励","充值成功，奖励你10个SCVC积分。","test.com",1,0,"",""),
	scvc_award_daily_bang(6,"scvcBang","参加帮帮奖励","申请帮帮成功，奖励你10个SCVC积分。(每天只奖励一次)","test.com",1,0,"",""),
	scvc_award_daily_skill(6,"scvcSkill","购买达人秀奖励","达人秀下单成功，奖励你10个SCVC积分。(每天只奖励一次)","test.com",1,0,"",""),
	scvc_award_daily_video(6,"scvcVideo","视频聊天奖励","今天你很活跃哦，奖励你10个SCVC积分。","test.com",1,0,"",""),
	
	/**
	 * 派单-达人接单管理
	 */
	master_receive_dispatch(7,"receiveDispatch","派单信息","XXX想和你一起MMM，预约金：YYY元。打开YoYo赶紧和Ta联系。","test.com",1,0,"",""),
	;
	
	/**
	 * 业务消息类型
	 */
	private int type;
	
	/**
	 * 业务消息代码
	 */
	private String code;
	/**
	 * 业务消息类型标题
	 */
	private String title;
	
	/**
	 * 业务消息类型简介
	 */
	private String intro;
	
	/**
	 * 打开链接地址
	 */
	private String openurl;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	private int opentype;
	
	/**
	 * 点击类型:0-不可点击,1-可点击
	 */
	private int hitType;
	
	/**
	 * 安卓页面名称
	 */
	private String androidAppPage;
	
	/**
	 * ios页面名称
	 */
	private String iosAppPage;
	
	private BussinessMessageTypeEnum(int type, String code, String title, String intro,String openurl,int opentype,int hitType,String androidAppPage,String iosAppPage) {
		this.type = type;
		this.code = code;
		this.title = title;
		this.intro = intro;
		this.openurl = openurl;
		this.opentype = opentype;
		this.hitType = hitType;
		this.androidAppPage = androidAppPage;
		this.iosAppPage = iosAppPage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}

	public int getOpentype() {
		return opentype;
	}

	public void setOpentype(int opentype) {
		this.opentype = opentype;
	}

	public int getHitType() {
		return hitType;
	}

	public void setHitType(int hitType) {
		this.hitType = hitType;
	}

	public String getAndroidAppPage() {
		return androidAppPage;
	}

	public void setAndroidAppPage(String androidAppPage) {
		this.androidAppPage = androidAppPage;
	}

	public String getIosAppPage() {
		return iosAppPage;
	}

	public void setIosAppPage(String iosAppPage) {
		this.iosAppPage = iosAppPage;
	}
	
	public static List<Integer> getTypes() {
		List<Integer> types = new ArrayList<>();
		//将小红点定义的类型加入到list集合中,为小红点接口统一查询服务
		types.add(BussinessMessageTypeEnum.my_publish.getType());
		types.add(BussinessMessageTypeEnum.my_purchase.getType());
		types.add(BussinessMessageTypeEnum.other_bussiness.getType());
		types.add(BussinessMessageTypeEnum.my_publish_free.getType());
		types.add(BussinessMessageTypeEnum.my_purchase_free.getType());
		
		return types;
	}
}
