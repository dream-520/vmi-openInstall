package com.tigerjoys.shark.miai.agent.constant;

import java.util.HashSet;
import java.util.Set;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;

/**
 * 常量类
 * @author chengang
 *
 */
public class Const {
	
	/**
	 * 是否是测试环境
	 */
	public static final boolean is_test = "offline".equals(ServiceConfig.getInstance().getString("env"));
	
	/**
	 * 我的页面活动菜单标号初始值
	 */
	public static final int MYPAGE_ACTIVITY_INDEXCODE_INIT = 10000;
	
	/**
	 * 提现收益附带奖励金倍率
	 */
	public static final int WITHDRAWAL_INCOME_BONUS_BASE = 1000;
	
	/**
	 * 充钻允许抵扣的收益比例
	 */
	public static final String RECHARGE_DIAMONDS_INCOME_RATIO = "RECHARGE_DIAMONDS_INCOME_RATIO";
	
	/**
	 * 图片域名
	 */
	public static final String HTTP_PIC_URL = ServiceConfig.getInstance().getString("image_url", "http://imgcdn.lanmifeng.com");
	
	/**
	 * 图片上传路径
	 */
	public static final String FILE_UPLOAD_DIR = ServiceConfig.getInstance().getString("file_upload_dir", "/data/vmi");
	
	/**
	 * 分成控制配置KEY
	 */
	public static final String VACUATE_CTRL = "VACUATE_CTRL";
	
	/**
	 * 钻石首充配置KEY
	 */
	public static final String CUSTORM_CATEGORY_FIRSTCHARGE = "CUSTORM_CATEGORY_FIRSTCHARGE";
	
	/**
	 * 小红花首充配置KEY
	 */
	public static final String REDFLOWER_FIRSTCHARGE = "REDFLOWER_FIRSTCHARGE";
	
	
	/**
	 * 互动转盘配值
	 */
	public static final String VCHAT_TURNTABLE_INFO = "VCHAT_TURNTABLE_INFO";
	
	
	/**
	 * 视频通话蒙层配置KEY
	 */
	public static final String VCHAT_ROOM_OBSCURATION = "VCHAT_ROOM_OBSCURATION";
	
	/**
	 * 默认的用户头像相对路径
	 */
	public static final String DEFAULT_USER_ICON = "/img/default_header.png";
	
	/**
	 * 每日不进行补助钻石配置KEY
	 */
	public static final String APP_USER_ALLOWANCE = "APP_USER_ALLOWANCE";
	
	/**
	 * 机器人行为配置
	 */
	public static final String APP_ROBOT_COFIG = "APP_ROBOT_COFIG";
	
	/**
	 * 首页打招呼消息策略
	 */
	public static final String APP_TOPPAGE_MSG_COFIG = "APP_TOPPAGE_MSG_COFIG";
	
	/**
	 * 每日进行补助收益抵扣配置KEY
	 */
	public static final String APP_USER_MONEY_ALLOWANCE = "APP_USER_MONEY_ALLOWANCE";
	
	/**
	 * 视频监黄业务配置key
	 */
	public static final String APP_CHECK_VIDEO_CONFIG = "APP_CHECK_VIDEO_CONFIG";
	
	
	/**
	 * 钻石不足提示信息
	 */
	public static final String HINT_INFO_DIAMOND_NOTENOUGH = "HINT_INFO_DIAMOND_NOTENOUGH";
	
	/**
	 * VIP弹窗提示信息
	 */
	public static final String HINT_INFO_VIP_POP = "HINT_INFO_VIP_POP";
	
	/**
	 * 进入音视频页提示信息 主播端
	 */
	public static final String HINT_INFO_ENTER_VCHAT_ANCHOR = "HINT_INFO_ENTER_VCHAT_ANCHOR";
	
	/**
	 * 进入音视频页提示信息 用户端
	 */
	public static final String HINT_INFO_ENTER_VCHAT_USER = "HINT_INFO_ENTER_VCHAT_USER";
	
	
	/**
	 * VIP弹窗开关
	 */
	public static final String VIP_POP_STATUS = "VIP_POP_STATUS";
	
	/**
	 * 任务推送分组前缀
	 */
	public static final String TASK_PUSH_TOPIC_PREFIX = (is_test ? "TEST" : "PROD") + "_TASK_";

	/**
	 * 视频域名
	 */
	public static final String HTTP_VCHAT_AUDIO_URL = ServiceConfig.getInstance().getString("audio_url" , "http://192.168.20.31:20000");
	
	
	/**
	 * 视频域名
	 */
	public static final String HTTP_VCHAT_PICTURE_URL = ServiceConfig.getInstance().getString("picture_url" , "http://192.168.20.31:20000");
	
	
	/**
	 * 网站地址
	 */
	public static final String WEB_SITE = ServiceConfig.getInstance().getString("web_site", "http://www.test.com");
	
	/**
	 * 异步通知回调网址
	 */
	public static final String PAY_NOTIFY_SITE = ServiceConfig.getInstance().getString("pay_notify_site", "http://192.168.20.31:20000");
	
	
	/**
	 * 获得默认的用户头像
	 * @return String
	 */
	public final static String getDefaultUserFace() {
		return HTTP_PIC_URL + DEFAULT_USER_ICON;
	}
	
	/**
	 * 特殊用户充值最小面值
	 */
	public final static Set<Long> USER_ID_CHARGE_PRICE_LIST = new HashSet<>();
	
	/**
	 * 特殊用户设备礼物列表
	 */
	public final static Set<String> USER_CLIENTID_GIFT_LIST = new HashSet<>();
	
	/**
	 * 公司测试号
	 */
	public final static Set<Long> USER_ID_GS_TEST_LIST = new HashSet<>();
	
	/**
	 * 工会收益流水
	 */
	public final static Set<Integer> LABOR_INCOME_TRANS_LIST = new HashSet<>();
	
	
	/**
	 * 邀请返现收益类型
	 */
	public final static Set<Integer> LABOR_ANCHOR_INVITER_INCOME_LIST = new HashSet<>();
	
	static{
		/*
		USER_ID_CHARGE_PRICE_LIST.add(143120116782006528L);
		USER_ID_CHARGE_PRICE_LIST.add(130114190751891712L);
		USER_ID_CHARGE_PRICE_LIST.add(32396088795267328L);
		USER_ID_CHARGE_PRICE_LIST.add(32470659676307712L);
		USER_ID_CHARGE_PRICE_LIST.add(145896550965510400L);
		USER_ID_CHARGE_PRICE_LIST.add(142626186703470848L);
		USER_ID_CHARGE_PRICE_LIST.add(132100703287050496L);
		USER_ID_CHARGE_PRICE_LIST.add(78111993137004800L);
		USER_ID_CHARGE_PRICE_LIST.add(91682454307406080L);
		USER_ID_CHARGE_PRICE_LIST.add(102018523049558272L);
		USER_ID_CHARGE_PRICE_LIST.add(134832417914552576L);
		USER_ID_CHARGE_PRICE_LIST.add(139341575525302528L);
		//测试用的
		USER_ID_CHARGE_PRICE_LIST.add(32397842511364352L);
		USER_ID_CHARGE_PRICE_LIST.add(87152674235023616L);
		USER_ID_CHARGE_PRICE_LIST.add(74258793973743872L);
		USER_ID_CHARGE_PRICE_LIST.add(135015798744875264L);
		USER_ID_CHARGE_PRICE_LIST.add(89142032517366016L);
		USER_ID_CHARGE_PRICE_LIST.add(33697911852302592L);
		USER_ID_CHARGE_PRICE_LIST.add(145352280982487296L);
		*/
		USER_CLIENTID_GIFT_LIST.add("edb68a0d8b6d7360080ea92a0a2d0b96");
		USER_CLIENTID_GIFT_LIST.add("0aaa1cb3fc753835d5ebb7e459dc2020");
		USER_CLIENTID_GIFT_LIST.add("01166b9eb8975f67d4145c44350528ab");
		USER_CLIENTID_GIFT_LIST.add("0da6e03af5e8b8bf4015c2de6ca37678");
		USER_CLIENTID_GIFT_LIST.add("5b3d99b8769239484e791a08287fca98");
		USER_CLIENTID_GIFT_LIST.add("2628e37af2a6b3417691fb518f6eb6c9");
		USER_CLIENTID_GIFT_LIST.add("0460dcdf7840f944d26ebdbd608b9195");
		USER_CLIENTID_GIFT_LIST.add("8342079c342205ab98c2f814dd27264c");
		USER_CLIENTID_GIFT_LIST.add("907d99dbf74299356a18159c0da72747");
		USER_CLIENTID_GIFT_LIST.add("2d8b258ef657eef619e0f340897ba6ed");
		
		USER_CLIENTID_GIFT_LIST.add("5ee0727ad0676b21f2c70908d2a5add9");
		USER_CLIENTID_GIFT_LIST.add("630b5c8d48b05faeb5cae967872c64f4");
		
		
		USER_CLIENTID_GIFT_LIST.add("24e7eddd0a97937eeac3359427561c08");
		
		
		USER_ID_GS_TEST_LIST.add(74258793973743872L);
		USER_ID_GS_TEST_LIST.add(32397842511364352L);
		USER_ID_GS_TEST_LIST.add(74259061893300480L);
		USER_ID_GS_TEST_LIST.add(145352280982487296L);
		USER_ID_GS_TEST_LIST.add(101824525378846976L);
		USER_ID_GS_TEST_LIST.add(107994552301846784L);
		USER_ID_GS_TEST_LIST.add(139341575525302528L);
		USER_ID_GS_TEST_LIST.add(132458211732160768L);
		
		
		////////////////////////
		
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.video_diamond.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.audio_diamond.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.text_chat_flower.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.gift_diamond.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.turntable_diamond.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.free_income_video_lt_30s.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.free_income_video_ge_30s.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.free_income_video_ge_30s_action2.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.point_transform_income.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.recharge_back_invitation_income.getLogType());
		//LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.anchor_settle_back_inviter_income.getLogType());
		LABOR_INCOME_TRANS_LIST.add(UserIncomeAccountLogTypeEnum.buy_guard_give_anchor_income.getLogType());
		
	
		
		LABOR_ANCHOR_INVITER_INCOME_LIST.add(UserIncomeAccountLogTypeEnum.video_diamond.getLogType());
		LABOR_ANCHOR_INVITER_INCOME_LIST.add(UserIncomeAccountLogTypeEnum.audio_diamond.getLogType());
		LABOR_ANCHOR_INVITER_INCOME_LIST.add(UserIncomeAccountLogTypeEnum.text_chat_flower.getLogType());
		LABOR_ANCHOR_INVITER_INCOME_LIST.add(UserIncomeAccountLogTypeEnum.gift_diamond.getLogType());
		LABOR_ANCHOR_INVITER_INCOME_LIST.add(UserIncomeAccountLogTypeEnum.turntable_diamond.getLogType());
		
		
		
	}
	
	
	
	
	
	
	
	/**
	 * 获得图片的根路径
	 * 
	 * @return String
	 */
	public final static String getCdn() {
		return HTTP_PIC_URL;
	}
	
	/**
	 * 获得CDN的绝对地址
	 * @param relativeUrl - 相对地址
	 * @return String
	 */
	public final static String getCdn(String relativeUrl) {
		if(relativeUrl == null || relativeUrl.length() == 0) {
			return HTTP_PIC_URL;
		}
		
		if(relativeUrl.charAt(0) == 'h') {
			return relativeUrl;
		} else {
			return HTTP_PIC_URL + relativeUrl;
		}
	}
	
	/**
	 * 获得语音消息的音频地址
	 * @param relativeUrl - String
	 * @return String
	 */
	public static String getVchatAudio(String relativeUrl ) {
		if(relativeUrl == null || relativeUrl.length() == 0) {
			return HTTP_VCHAT_AUDIO_URL;
		}
		if(relativeUrl.charAt(0) == 'h') {
			return relativeUrl;
		} else {
			return HTTP_VCHAT_AUDIO_URL + relativeUrl;
		}
	}
	
	
	/**
	 * 获得语音消息的音频地址
	 * @param relativeUrl - String
	 * @return String
	 */
	public static String getVchatPicture(String relativeUrl ) {
		if(relativeUrl == null || relativeUrl.length() == 0) {
			return HTTP_VCHAT_PICTURE_URL;
		}
		if(relativeUrl.charAt(0) == 'h') {
			return relativeUrl;
		} else {
			return HTTP_VCHAT_PICTURE_URL + relativeUrl;
		}
	}

	/**
	 * 获得系统消息的url地址
	 * @param messId - MessageTemplateEntity的ID
	 * @return String
	 */
	public final static String getSysMessUrl(long messId) {
		return Const.WEB_SITE+"/api/sysMessage/message/"+messId;
	}
	
	/**
	 * 普通约会列表分页的pagesize
	 */
	public static final int free_appoint_pagesize = 10;

	/**
	 * 视频聊天每分钟花费钻石数量
	 */
	public static final int USER_VIDEO_CHAT_DIAMONDS_COST = 120;
	
	/**
	 * yoyo A包名
	 */
	public static final String A = "com.ydwx.yoyo";
	
	/**
	 * yoyo B1包名
	 */
	public static final String B1 = "com.ydwx.yoyo2";
	
	/**
	 * yoyo B2包名
	 */
	public static final String B2 = "com.ydwx.yoyo3";
	
	
	
}
