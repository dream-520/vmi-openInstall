/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.utils.Tools;

/** 
 * ClassName: UserIncomeAccountLogTypeEnum <br/> 
 * date: 2017年5月10日 下午7:04:44 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserIncomeAccountLogTypeEnum {
	unknow(AccountType.GENERAL, "未知",0),
	service(AccountType.GENERAL, "服务收益",1),
	packet_diamond(AccountType.GENERAL, "钻石红包", 2),
	tip(AccountType.GENERAL,"小费", 3),
	cash_withdrawal(AccountType.GENERAL,"提现收益", 4),
	cash_return(AccountType.GENERAL, "收益回滚", 5),
	talent_award(AccountType.GENERAL, "过审达人奖励", 6),
	
	general_award(AccountType.GENERAL, "任务奖励收益", 7),
	task_withdrawal(AccountType.TASK,"任务提现收益", 8),
	task_return(AccountType.TASK, "任务收益回滚", 9),
	task_award(AccountType.TASK, "任务奖励收益", 10),
	
	paid_car_fare(AccountType.GENERAL, "付费约车费收益", 11),
	paid_compensate(AccountType.GENERAL, "取消订单补偿收益", 12),
	
	bonus_award(AccountType.BONUS, "奖励金收益", 13),
	bonus_withdrawal(AccountType.BONUS,"奖励金提现", 14),
	bonus_return(AccountType.BONUS, "奖励金回滚", 15),

	chat_diamond(AccountType.GENERAL, "视频聊天钻石收益", 16),
	buy_diamond(AccountType.GENERAL, "购买钻石", 17),
	gift_diamond(AccountType.GENERAL, "礼物收益", 18),
	roulette_wheel(AccountType.GENERAL, "轮盘抽奖收益", 19),
	hundred_responses(AccountType.GENERAL, "一呼百应收益", 20),
	recharge_back_income(AccountType.GENERAL, "冲钻返收益", 21),
	text_diamond(AccountType.GENERAL, "图文聊天收益", 22),
	audio_diamond(AccountType.GENERAL, "音频聊天收益", 23),
	video_diamond(AccountType.GENERAL, "视频聊天收益", 24),
	recharge_back_invitation_income(AccountType.GENERAL, "冲钻返邀请者收益", 25),
	
	transfer_income(AccountType.GENERAL, "子账户转主账户", 26),
	sign_award(AccountType.GENERAL, "签到奖励",27),
	
	new_user_award(AccountType.GENERAL, "新用户奖励",28),
	
	anchor_trans_pay_user(AccountType.GENERAL, "转化提成",29),
	
	turntable_diamond(AccountType.GENERAL, "转盘收益", 30),
	
	anchor_trans_call_user(AccountType.GENERAL, "接听免费视频奖励",31),
	
	recharge_flower_back_income(AccountType.GENERAL, "购买小红花返收益", 32),
	
	buy_red_flower(AccountType.GENERAL, "购买小红花", 33),
	
	text_chat_flower(AccountType.GENERAL, "小红花文字聊天收益", 34),
	
	recharge_for_deduction(AccountType.GENERAL, "充值抵扣金", 35),
	
	free_income_video_lt_30s(AccountType.GENERAL, "通话激励", 36),  //方案1 小于30S的 
	
	free_income_video_ge_30s(AccountType.GENERAL, "通话激励", 37),  //方案1 大于等于30S的 
	
	point_transform_income(AccountType.GENERAL, "积分换收益", 38),  
	
	free_income_video_ge_30s_action2(AccountType.GENERAL, "通话激励", 39),  // 方案2 大于等于30S的 
	

	anchor_settle_back_inviter_income(AccountType.GENERAL, "邀请大V分成",40),

	income_buy_guard(AccountType.GENERAL, "购买守护", 41),  // 收益购买守护
	
	
	
	web_roulette_award(AccountType.GENERAL, "大转盘抽中抵扣券", 42),
	
	buy_guard_give_anchor_income(AccountType.GENERAL, "守护分成", 43),
	
	user_inivite_cps_back_Income(AccountType.GENERAL, "邀请分成", 44),
	
	income_buy_vip(AccountType.GENERAL, "购买VIP", 45),  // VIP
	
	income_buy_week_card(AccountType.GENERAL, "购买周卡", 46), 
	
	income_buy_dial_experience(AccountType.GENERAL, "畅聊体验", 47),  
	
	income_deduct(AccountType.GENERAL, "收益人工扣除", 48),
	income_increase(AccountType.GENERAL, "收益人工增加", 49),
	
	user_look_anchor_contact(AccountType.GENERAL, "用户查看主播联系方式收益", 50),  //用户查看主播联系方式
	
	user_lock_anchor_privacy_photo(AccountType.GENERAL, "用户查看主播私密相册收益", 51),
	user_lock_anchor_privacy_video(AccountType.GENERAL, "用户查看主播私密视频收益", 52),
	
	recharge_vip_back_invitation_income(AccountType.GENERAL, "购买VIP返邀请者收益", 53),
	;
	
	/**
	 * 账户类型：1-通用收益账户；2-任务收益账户(单独提现)；3-奖励金
	 */
	private AccountType accountType;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 记录分类 
	 */
	private int logType;

	private static final Map<Integer, UserIncomeAccountLogTypeEnum> tc = new HashMap<>();
	
	private UserIncomeAccountLogTypeEnum(AccountType accountType, String desc, int logType) {
		this.accountType = accountType;
		this.desc = desc;
		this.logType = logType;
	}
	
	
	static {
		for(UserIncomeAccountLogTypeEnum refer : UserIncomeAccountLogTypeEnum.values()) {
			tc.put(refer.getLogType(), refer);
		}
	}
	
	public static UserIncomeAccountLogTypeEnum getByCode(int logType) {
		UserIncomeAccountLogTypeEnum logTypeEnum = tc.get(logType);
		if(Tools.isNull(logTypeEnum)){
			return UserIncomeAccountLogTypeEnum.unknow;
		}
		return logTypeEnum;
	}

	public int getAccountType() {
		return accountType.getCode();
	}

	public void setAccountType(int accountType) {
		this.accountType = AccountType.getByCode(accountType);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}
	
	/**
	 * 是否是通用账户
	 * @return
	 */
	public boolean isGeneralAccount(){
		return AccountType.GENERAL.getCode() == this.getAccountType();
	}
	
	/**
	 * 账户类型
	 * ClassName: AccountType <br/> 
	 * date: 2017年8月23日 上午9:45:42 <br/> 
	 * 
	 * 账户类型：1-通用收益账户；2-任务收益账户(单独提现)；3-奖励金
	 * @author mouzhanpeng 
	 * @version UserIncomeAccountLogTypeEnum 
	 * @since JDK 1.8.0
	 */
	public static enum AccountType{
		GENERAL(1),TASK(2),BONUS(3);
		
		private int code;
		
		private AccountType(int code){
			this.code=code;
		}
		
		public int getCode(){
			return code;
		}
		
		public static AccountType getByCode(int code){
			for(AccountType type : AccountType.values()){
				if(code == type.getCode()){
					return type;
				}
			}
			return null;
		}
	}
	
}
