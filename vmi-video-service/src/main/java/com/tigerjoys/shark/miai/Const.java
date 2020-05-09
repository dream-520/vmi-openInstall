package com.tigerjoys.shark.miai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shark.miai.common.dto.IdNameBooleanDto;
import org.shark.miai.common.enums.AppNameEnum;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.dto.service.VipBuyExperienceDto;

/**
 * 常量类
 * @author chengang
 *
 */
public class Const {
	
	/**
	 * 是否是测试环境
	 */
	public static final boolean IS_TEST = "offline".equals(ServiceConfig.getInstance().getString("env","offline"));
	
	/**
	 * 我的页面活动菜单标号初始值
	 */
	public static final int MYPAGE_ACTIVITY_INDEXCODE_INIT = 10000;
	
	/**
	 * 图片域名
	 */
	public static final String HTTP_PIC_URL = ServiceConfig.getInstance().getString("image_url", "http://192.168.20.31:20000");
	
	/**
	 * 视频域名
	 */
	public static final String HTTP_VIDEO_URL = ServiceConfig.getInstance().getString("video_url" , "http://192.168.20.31:20000");
	
	
	/**
	 * 视频域名
	 */
	public static final String HTTP_VCHAT_AUDIO_URL = ServiceConfig.getInstance().getString("audio_url" , "http://192.168.20.31:20000");
	
	/**
	 * plist文件域名
	 */
	public static final String PLIST_URL_HTTPS = ServiceConfig.getInstance().getString("plist_url_https" , "http://192.168.20.31:20000");
	
	
	/**
	 * 上传文件临时保存路径
	 */
	public static final String TEMP_FILE_UPLOAD_DIR = ServiceConfig.getInstance().getString("temp_upload_dir", "/tmp/vmi");
	
	/**
	 * 网站接口地址
	 */
	public static final String WEB_SITE = ServiceConfig.getInstance().getString("web_site", "http://www.test.com");
	
	/**
	 * 网站访问地址,如：分享等需要用户在外部访问
	 */
	public static final String VISIT_SITE = ServiceConfig.getInstance().getString("visit_site" , "http://www.test.com");
	
	/**
	 * 监黄上传网址
	 */
	public static final String CHECK_PORN_UPLOAD = ServiceConfig.getInstance().getString("check_porn_upload" , "http://www.test.com");
	
	/**
	 * 语音监控上传网址
	 */
	public static final String CHECK_AUDIO_UPLOAD = ServiceConfig.getInstance().getString("check_audio_upload" , "http://www.test.com");
	
	/**
	 * 聊天图片监
	 */
	public static final String CHECK_PHOTO_UPLOAD = ServiceConfig.getInstance().getString("check_photo_upload" , "http://www.test.com");
	
	/**
	 * 微信的html页面的APPID
	 */
	public static final String WEIXIN_HTML_APPID = ServiceConfig.getInstance().getString("wx_html_appid" , "123456789");
	
	/**
	 * 微信的html页面的密钥
	 */
	public static final String WEIXIN_HTML_APPSECRET = ServiceConfig.getInstance().getString("wx_html_secret" , "123456789");
	
	/**
	 * APP分享地址
	 */
	public static final String APP_SHARE_PATH =WEB_SITE+"/web/BshareIndex";
	
	/**
	 * 默认的用户头像相对路径
	 */
	public static final String DEFAULT_USER_ICON = "/img/default_header.png";
	
	/**
	 * 一些内容的CDN URL占位符
	 */
	public static final String CONTENT_CDN_URL_PLACEHOLDER = "#{CDN}";
	
	/**
	 * 赠送红花数
	 */
	public static final int RED_FLOWERS_DAILY = 5;
	
	/**
	 * 每次聊天消耗红花数
	 */
	public static final int RED_FLOWERS_EVERY_TIME = 1;

	/**
	 * 轮盘抽奖消耗钻数
	 */
	public static final int ROULETTE_DIAMONDS_EACH_TIME = 20;
	
	/**
	 * 轮盘抽奖消耗钻石数量
	 */
	public static final int WEB_ROULETTE_DIAMONDS_EACH_TIME = 2*20;

	/**
	 * 充钻允许抵扣的收益比例
	 */
	public static final float RECHARGE_DIAMONDS_INCOME_RATIO = 0.50F;
	
	/**
	 * 姻缘碰碰碰抽奖消耗钻数
	 */
	public static final int FATE_DIAMONDS_EACH_TIME = 20;
	
	/**
	 * 老虎机抽奖消耗钻数
	 */
	public static final int SLOT_DIAMONDS_EACH_TIME = 2*20;
	
	/**
	 * 提现限额
	 */
	public static final int WITHDRAWAL_LIMIT = 100;
	
	/**
	 * 领话费
	 */
	public static final long GET_PHONE_MONEY  = 2000;
	
	/**
	 * 任务提现限额
	 */
	public static final int TASK_WITHDRAWAL_LIMIT = 1;
	
	/**
	 * 任务提现时间限制
	 */
	public static final int TASK_WITHDRAWAL_DATE_LIMIT = 7;
	
	/**
	 * vip查看联系方式上限
	 */
	public static final int LOOK_CONTACTS_LIMIT = 3;
	
	/**
	 * 新用户注册赠送额度
	 */
	public static final int USER_CREAT_ADD_BALANCE = 100;
	
	/**
	 * 视频聊天每分钟花费钻石数量
	 */
	public static final int USER_VIDEO_CHAT_DIAMONDS_COST = com.tigerjoys.shark.miai.agent.constant.Const.USER_VIDEO_CHAT_DIAMONDS_COST;

	/**
	 * 图文聊天耗费钻石数
	 */
	public static final int USER_TEXT_CHAT_DIAMONDS_COST = 1;

	/**
	 * 语音聊天耗费钻石数
	 */
	public static final int USER_AUDIO_CHAT_DIAMONDS_COST = 25;

	/**
	 * 付费查看信息
	 */
	public static final int USER_CHECK_RIGHT_DIAMOND = 100;

	/**
	 * 付费查看信息
	 */
	public static final int USER_CHECK_RIGHT_ENERGY = 100;
	
	/**
	 * 私密相册查看花费
	 */
	public static final int USER_PHOTO_PRIVACY_DIAMOND = 100;
	
	/**
	 * 私密视频查看花费
	 */
	public static final int USER_VIDEO_PRIVACY_DIAMOND = 200;

	/**
	 * 应用首屏
	 */
	public static final String APP_AD_BANNER = "app_ad_banner";
	
	/**
	 * 新用户送钻
	 */
	public static final String NEW_USER_AWARD = "new_user_award";
	
	/**
	 * 所有渠道，此处是简称。具体看t_app_channel表
	 */
	public static final String ALL_CAHNNEL = "all";
	
	/**
	 * 所有渠道，此处是简称。具体看t_app_channel表
	 */
	public static final String SHARE_URL = WEB_SITE+"/api/share/";
	
	/**
	 * 用户Token过期毫秒数
	 */
	public static final long USER_TOKEN_EXPIRE_MILLIS = Tools.WEEK_MILLIS;
	
	/**
	 * 付费约购买者至少需要N信用才能进行付费购买
	 */
	public static final int PAID_APPOINT_BUYER_CREDIT_CONDITION = 60;
	
	/**
	 * 付费约签到的要求的距离，米
	 */
	public static final int PAID_APPOINT_SIGN_DISTANCE_METRE = 500;
	
	/**
	 * 达人VIP已过期的提示信息
	 */
	public static final String TALENT_VIP_EXPIRED_INFO = "您的达人VIP已过期，请及时续费";
	
	/**
	 * 达人VIP已过期的提示信息
	 */
	public static final String TALENT_VIP_EXPIRED_FORDISPATCH_INFO = "您的达人VIP已过期，不能够接受派单哦!去续费";
	
	/**
	 * 达人VIP不足指定的天数的提示信息
	 */
	public static final String TALENT_VIP_OUT_OF_DATE_INFO = "您的达人VIP已不足%d天，请及时续费";
	
	/**
	 * 从来没有买过达人VIP的提示信息
	 */
	public static final String TALENT_VIP_NO_BUY_INFO = "您尚未拥有达人VIP，请购买";
	
	/**
	 * 达人VIP未过期的提示信息
	 */
	public static final String TALENT_VIP_NO_EXPIRE_INFO = "您的达人VIP有效期至：%s";
	
	/**
	 * 达人VIP快过期提示的天数
	 */
	public static final int TALENT_VIP_EXPIRE_TIP_DAY = 3;
	
	/**
	 * 达人VIP服务者信用不足的提示信息
	 */
	public static final String TALENT_VIP_CREDIT_NOT_ENOUGH_INFO = "您的信用分过低，美女帅哥们不能看到你的约会了";
	
	/**
	 * 新用户注册赠送3个月的达人VIP
	 */
	public static final int NEWUSER_GIFT_TALENT_VIP_MONTHS = 3;
	
	/**
	 * 新用户注册赠送7天普通VIP权限
	 */
	public static final int NEWUSER_GIFT_VIP_DAY = 7;
	
	/**
	 * 用户信用记录每页数据量
	 */
	public final static int CREDIT_RECORD_PAGESIZE = 10;
	
	/**
	 * 城市版本编号，如果改变则会造成客户端城市的升级
	 */
	public final static int CITY_CODE_VERSION = 2;
	
	
	/**
	 * 老用户钻石价格
	 */
	public final static long OLD_USER_RECHARGE_CUSTOM_CATEGORY = 12;
	
	/**
	 * 首充配值
	 */
	public static final String CUSTORM_CATEGORY_FIRSTCHARGE = "CUSTORM_CATEGORY_FIRSTCHARGE";
	
	
	/**
	 * 约定的城市文件下载的相对地址
	 */
	public final static String CITY_DOWNLOAD_FILE_URL = "/static/files/city.zip";
	
	/**
	 * IOS测试帐号固定验证码登录
	 */
	//public final static Map<String , String> IOS_TEST_MOBILE_ACCOUNT_MAP = new HashMap<>();
	
	/**
	 * IOS测试帐号大V助手，自动拨打禁止拨打
	 */
	//public final static Map<Long , String> IOS_TEST_USERID_MAP = new HashMap<>();
	
	
	
	
	/**
	 * 联系方式字段
	 */
	public static final String SETTING_CONTACT_INFO = "setting_contact_info";
	
	/**
	 * 联系方式字段
	 */
	public static final String PRIVACY_EXPLAIN_INFO = "privacy_explain_info";
	
	/**
	 * 联系方式字段
	 */
	public static final String IOS_TEST_PRIVACY_EXPLAIN_INFO = "ios_test_privacy_explain_info";
	
	
	/**
	 * 主播评价用户提示
	 */
	public static final String ANCHOR_TO_USER_EVALUATION_INFO = "ANCHOR_TO_USER_EVALUATION_INFO";
	
	/**
	 * 购买私密相册和视频余额不足或非VIP的提示信息
	 */
	public static final String LOOK_PRIVACY_PHOTO_HIT_INFO = "LOOK_PRIVACY_PHOTO_HIT_INFO";
	
	/**
	 * 机器人手机号
	 */
	public static final Set<String> robotPhone;
	
	/**
	 * 九个假主播
	 * */
	public static final Set<Long> manAhcnor;
	/**
	 * 机器人手机号
	 */
	public static  final VipBuyExperienceDto vipBuyExperienceDto = VipBuyExperienceDto.preDto(Long.MAX_VALUE,18L,7L);
	
	
	/**
	 * VIP 状态开关  true  开启  false 关闭
	 */
	//public final static boolean VIP_STATUS = true;
	
	/**
	 * 机器人统一的验证码
	 */
	public final static String ROBOT_PHONE_CODE = "3562";
	
	/**
	 * ios达人约预约金常量
	 */
	public final static int PAID_APPOINT_GOLD = 300;
	
	/**
	 * 当天购买体验机会价格(元)
	 */
	public final static long BUY_DIAL_EXPERIENCE_CURRENT_DAY = 1;
	
	/**
	 * 当天某些省份购买体验机会价格(元)
	 */
	public final static long BUY_DIAL_EXPERIENCE_PROVINCE_CURRENT_DAY = 2;
	
	/**
	 * 第二天内购习体验机会价格(元)
	 */
	public final static long BUY_DIAL_EXPERIENCE_LAST_15_DAY = 5;
	/**
	 * 付费约下单页面温馨提示
	 */
	public final static String PAID_APPOINT_BUY_TIPS = "" +
			"注意事项：\n" + 
			"1、约会过程中，请合理消费；\n" + 
			"2、涉及到饮酒、极限运动、或其它安全性行为，请勿强制；\n" + 
			"3、建议提前预定场所、避免影响约会；\n" + 
			"4、双方应保持相互尊重和礼貌，及时沟通；";
	
	/**
	 * 付费约支付页面温馨提示
	 */
	public final static String PAID_APPOINT_PAY_NOTICE = "注意：\n请礼貌约会，互相尊重";
	
	/**
	 * 付费约下单页面车费选项
	 */
	public final static List<IdNameBooleanDto> PAID_APPOINT_CAR_FARE_LIST;
	
	/**
	 * 支付测试帐号
	 */
	//public final static Map<Long , String> PAY_TEST_MOBILE_ACCOUNT_MAP = new HashMap<>();
	
	/**
	 * 账户限制充值起始金额 用户
	 */
	public final static Map<Long , String> USER_LIMIT_STAET_RECHARGE_MONEY_ACCOUNT_MAP = new HashMap<>();
	
	/**
	 * 账户限制充值起始金额  设备
	 */
	public final static Map<String , String> USER_LIMIT_STAET_RECHARGE_MONEY_CLIENT_MAP = new HashMap<>();
	
	
	/**
	 * 账户限制充值起始金额  IP
	 */
	public final static Map<String , String> USER_LIMIT_STAET_RECHARGE_MONEY_IP_MAP = new HashMap<>();
	
	
	
	/**
	 * 用户禁止充值
	 */
	public final static Map<String , String> USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP = new HashMap<>();
	
	
	/**
	 * IOS提审需隐藏的包信息
	 */
	public final static Map<String , String> IOS_TEST_HIDE_INFO_MAP = new HashMap<>();
	

	/**
	 * IOS提审账户充钻1比1
	 */
	public final static Map<Long , String> IOS_TEST_CHARGE_DIAMOND = new HashMap<>();
	
	
	/**
	 * 北京和天津的LV3以下的用户不被以下主播拨打
	 */
	public final static Map<Long , String> ANCHOR_DIAL_FORBID_USERID_MAP = new HashMap<>();
	
	static {
		robotPhone = new HashSet<>();
		robotPhone.add("13500000001");
		robotPhone.add("13500000002");
		robotPhone.add("13500000003");
		robotPhone.add("13500000004");
		robotPhone.add("13500000005");
		robotPhone.add("13500000006");
		robotPhone.add("13500000007");
		robotPhone.add("13500000008");
		robotPhone.add("13500000009");
		robotPhone.add("13500000010");
		
		manAhcnor = new HashSet<>();
		manAhcnor.add(79326890336256256L);
		manAhcnor.add(79326894041923840L);
		manAhcnor.add(79326899624542464L);
		manAhcnor.add(79326898708087040L);
		manAhcnor.add(79326889660973312L);
		manAhcnor.add(65418713444122368L);
		manAhcnor.add(79326894761246976L);
		manAhcnor.add(65418716405300992L);
		manAhcnor.add(79326902485057792L);

		
		for(int i = 1; i <= 120; i++) {
			robotPhone.add(13500000000L + i + "");
		}
		
		//付费约车费选项
		PAID_APPOINT_CAR_FARE_LIST = new ArrayList<IdNameBooleanDto>();
		PAID_APPOINT_CAR_FARE_LIST.add(new IdNameBooleanDto(1, "20元", "20" , true));
		PAID_APPOINT_CAR_FARE_LIST.add(new IdNameBooleanDto(2, "50元", "50"));
		PAID_APPOINT_CAR_FARE_LIST.add(new IdNameBooleanDto(3, "100元", "100"));
		PAID_APPOINT_CAR_FARE_LIST.add(new IdNameBooleanDto(4, "200元", "200"));
		
		//ios测试账号验证码

		/*
		//IOS_TEST_MOBILE_ACCOUNT_MAP.put("15873952675", "1234");
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("18811315514", "6666");
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("15873952675", "1234");
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("13688939043", "1111");
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("13789392092", "1234");
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("15740595630", "6666");
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("18749206132", "1111");
		
		
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("15774029611", "7531");  //明龙普通用户
		IOS_TEST_MOBILE_ACCOUNT_MAP.put("18398327856", "1111");  //夏军
		*/
		
		
		/////////////////////
		USER_LIMIT_STAET_RECHARGE_MONEY_ACCOUNT_MAP.put(161583082667835648L, "user");
		//USER_LIMIT_STAET_RECHARGE_MONEY_ACCOUNT_MAP.put(157661844484456704L, "text");
		
		USER_LIMIT_STAET_RECHARGE_MONEY_CLIENT_MAP.put("4767396ef31766c46c985a32b4e5945b", "user");
		
		
		//USER_LIMIT_STAET_RECHARGE_MONEY_IP_MAP.put("1.180.156.86","user");
		/////////////////////////
	
		/*	
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(161277317641994752L, "15873967123");
		
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(130114190751891712L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(32396088795267328L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(32470659676307712L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(145896550965510400L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(142626186703470848L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(132100703287050496L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(78111993137004800L, "online_test");
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(91682454307406080L, "online_test");
		
		PAY_TEST_MOBILE_ACCOUNT_MAP.put(156893542126518528L, "offline_test");
		*?
		//IOS提审大V助手，自动拨打禁止拨打
		/*
		IOS_TEST_USERID_MAP.put(156227137842512384L, "18811315514");	//大V助手
		IOS_TEST_USERID_MAP.put(163069912242258176L, "13688939043");   //对对
		IOS_TEST_USERID_MAP.put(150039910194151680L, "15873952675");  //蜜友
		IOS_TEST_USERID_MAP.put(171239105804435968L, "13789392092");  //蜜恋
		IOS_TEST_USERID_MAP.put(171976577914044928L, "15740595630");  //缘缘
		IOS_TEST_USERID_MAP.put(173585561538330880L, "18749206132");  //趣聊交友
		
		//IOS_TEST_USERID_MAP.put(161277317641994752L, "线上测试");
		IOS_TEST_USERID_MAP.put(33697911852302592L, "线下测试");
		*/
		//IOS提审信息隐藏的包信息
		IOS_TEST_HIDE_INFO_MAP.put("com.duidui.duijiaoyou", "ios_duidui");
	
		/*
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("163763228044755200", "userId");
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("163578174150607104", "userId");
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("d5b748e4bc3beeaf426624b95aee0f89", "clientid");
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("223.91.195.36", "IP");
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("223.91.195.226", "IP");
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("74258793973743872", "testUser");
		USER_STOP_RECHARGE_USERID_CLIENT_IP_MAP.put("159805667281010944", "testUser");
		*/
		
		ANCHOR_DIAL_FORBID_USERID_MAP.put(154392008136392960L, "onlineUser");
		ANCHOR_DIAL_FORBID_USERID_MAP.put(132484736267387136L, "onlineUser");
		ANCHOR_DIAL_FORBID_USERID_MAP.put(153165189141823744L, "onlineUser");
		//ANCHOR_DIAL_FORBID_USERID_MAP.put(32441318881952000L, "testOnlineUser");
		//ANCHOR_DIAL_FORBID_USERID_MAP.put(134831947223466240L, "testUser");
		
		//IOS 提审根据价格对应钻石数
		IOS_TEST_CHARGE_DIAMOND.put(156227137842512384L, "18811315514");
		IOS_TEST_CHARGE_DIAMOND.put(90377550552957184L, "test");
		
	}
	
	/**
	 * 获得默认的用户头像
	 * @return String
	 */
	public final static String getDefaultUserFace() {
		return HTTP_PIC_URL + DEFAULT_USER_ICON;
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
     * 提现金额限制
     */
    public static final long EXTRACT_LOWER_BOUND = 50;
    
    /**
     * 获得服务器可访问网址
     * @return String
     */
    public final static String getVisitSite() {
    	return VISIT_SITE;
    }
	
    /**
     * 获得服务器接口地址
     * @return String
     */
    public final static String getWebSite() {
    	return WEB_SITE;
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
	 * 获取ta的动态的url地址
	 */
	public final static String getTaDynamic(long userid){
		return Const.WEB_SITE + "/api/dynamic/tadynamic?userid=" + userid;
	}
	
	/**
	 * 获取my的动态的url地址
	 */
	public final static String getMyDynamic(){
		return Const.WEB_SITE + "/api/dynamic/mydynamic";
	}
	
	/**
	 * 获取发现栏目的动态的url地址
	 */
	public final static String getDynamic(){
		return Const.WEB_SITE + "/api/dynamic/dynamic";
	}
	
	/**
	 * 第一次派单的支付页描述
	 */
	public final static String FIRST_DISPATCH_DDESC = "(第一次用，只需先交10%金额哦)";
	
	/**
	 * 签到卡价格
	 */
	public static final int SIGN_CARD_PRICE = 5;
	
	
	public static String getProtocol(String path){
		Map<String, String> hsmp = new HashMap<>();
		String protocolPath = "";
		hsmp.put(AppNameEnum.ios_app_yoyo_a1.getPackageName(), "0");
		hsmp.put(AppNameEnum.ios_com_yoyo_yushen.getPackageName(), "1");
		hsmp.put(AppNameEnum.ios_com_yo_miliao.getPackageName(), "2");
		//hsmp.put(AppNameEnum.ios_com_yo_miliaoliao.getPackageName(), "1");
		hsmp.put(AppNameEnum.ios_com_hailiao_hailiao.getPackageName(), "3");
		hsmp.put(AppNameEnum.ios_com_ailiao_ailiao.getPackageName(), "4");
		hsmp.put(AppNameEnum.ios_com_vmiliao_vmiliao.getPackageName(), "5");
		hsmp.put(AppNameEnum.ios_com_paopao_paopao.getPackageName(), "6");
		hsmp.put(AppNameEnum.andriod_com_ydwx_yoyo.getPackageName(), "");
		hsmp.put(AppNameEnum.andriod_com_tjhj_miliao.getPackageName(), "1");
		if(Tools.isNotNull(hsmp.get(path))){
			protocolPath = "protocol/protocol"+hsmp.get(path);
		}
		return protocolPath;
	}
	
	public static String getNewAppName(String path){
		return AppNameEnum.getByDesc(path);
	}

	
}
