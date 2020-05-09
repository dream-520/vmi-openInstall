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
public enum RedFlowerAccountLogTypeEnum {

	unknown(0,"未知"),
	recharge_account(1, "账户充值"),
	anchor_star_consumption(2, "女神之星投票消费"),
	register_user(3, "注册赠送小红花"),
	user_chat_consumption(4, "用户聊天"),
	message_reward(5, "客户消息系统奖励"),
	web_roulette(6, "大转盘投注消耗"),
	recharge_guard_give_redflower(7, "购守护赠送小红花"),
	recharge_vip_give_redflower(8, "购VIP赠送小红花"),
	recharge_guard_vip_give_redflower(9, "购守护和VIP赠送小红花"),
	recharge_week_card_give_redflower(10, "购周卡赠送小红花"),
	chat_audio_consumption(11, "聊天界面发送音频消费"),
	look_audio_consumption(12, "用户查看音频消费"),
	chat_picture_consumption(11, "聊天界面发送图片消费"),
	//不要超过这个值
	test(100 , "测试"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> descs = new HashMap<Integer , String>();
	
	static {
		for(RedFlowerAccountLogTypeEnum refer : RedFlowerAccountLogTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private RedFlowerAccountLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static RedFlowerAccountLogTypeEnum getByCode(int code) {
		for (RedFlowerAccountLogTypeEnum refer : RedFlowerAccountLogTypeEnum.values())
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
