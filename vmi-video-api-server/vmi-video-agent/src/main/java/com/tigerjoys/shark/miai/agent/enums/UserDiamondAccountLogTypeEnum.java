/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: UserDiamondAccountLogTypeEnum <br/> 
 * date: 2017年5月10日 下午7:04:44 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserDiamondAccountLogTypeEnum {

	unknown(0,"未知"),
	recharge_account(1, "账户充值"),
	pay_talent_vip(2 , "购买达人VIP"),
	purchase_credit_score(3 , "购买信用分"),
	pay_vip(4 , "购买VIP"),
	stick_user(5 , "用户置顶"),
	pay_honesty_badge(6,"购买诚信徽章"),
	free_apply_order(10 , "普通约申请者红包"),
	free_posted_order(11 , "普通约发布者红包" ),
	free_apply_order_refund(12 , "普通约申请者红包退款" ),
	free_posted_order_refund(13 , "普通约发布者红包退款"),
	free_apply_order_received_redbag(14 , "普通约申请者收到红包"),
	free_posted_order_received_redbag(15 , "普通约发布者收到红包" ),
	pay_red_flower(16 , "购买小红花"),
	pay_lucky_Draw(17 , "购买幸运抽奖"),
	lucky_Draw_get(18 , "幸运抽奖获得"),
	video_chat(19, "视频聊天"),
	check_user(20, "付费查看信息"),
	world_cup_guess(21, "世界杯竞猜"),
	world_cup_guess_gain(22, "世界杯竞猜奖励"),
	chat_gift(23, "聊天刷礼物"),
	roulette_wheel_cost(24, "轮盘抽奖"),
	a_hundred_responses_cost(25, "一呼百应"),
	fate_roulette_wheel_cost(26, "姻缘对对碰抽奖"),
	txt_chat(27,"文本聊天"),
	audio_chat(28,"音频聊天"),
	appointment_vote(29,"约会投注"),
	pay_sign_card(30 , "购买签到卡"),
	reg_award(31 , "新用户注册赠送"),
	Diamond_transfer(32 , "钻石副转主"),
	zfb_charge_award(33 , "支付宝充值奖励"),
	chat_turntable(34, "互动转盘"),
	godness_activity_award(35, "女神之星活动奖励"),
	pay_limit_award(36, "累计充值500送钻石"),
	web_roulette_award(37, "大转盘活动奖励"),
	point_transform_diamond(38, "积分换钻"),
	message_reward(40, "客户消息系统奖励"),
	
    slot_machine_cost(41, "老虎机抽奖消费"),
    slot_machine_award(42, "老虎机抽奖奖励"),
    user_subscribe_anchor_cost(43, "用户预约主播预约金消费"),
    user_subscribe_anchor_return(44, "用户预约主播预约金返回"),
    user_super_subscribe_anchor_cost(45, "用户预约金牌主播预约金消费"),
    user_super_subscribe_anchor_return(46, "用户预约金牌主播预约金返回"),
    user_look_anchor_contact(47, "用户查看主播联系方式"),
    buy_vip_give_diamond(48, "购买VIP送钻石"),
    user_pay_privacy_video(49,"用户购买私密视频"),
    user_pay_privacy_photo(50,"用户购买私密相册"),
	//不要超过这个值
	test(100 , "测试"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> descs = new HashMap<Integer , String>();
	
	static {
		for(UserDiamondAccountLogTypeEnum refer : UserDiamondAccountLogTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserDiamondAccountLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static UserDiamondAccountLogTypeEnum getByCode(int code) {
		for (UserDiamondAccountLogTypeEnum refer : UserDiamondAccountLogTypeEnum.values())
			if (code == refer.getCode())
				return refer;
		return unknown;
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
