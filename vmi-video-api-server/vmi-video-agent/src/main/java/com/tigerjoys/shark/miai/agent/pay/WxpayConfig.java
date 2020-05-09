package com.tigerjoys.shark.miai.agent.pay;

import java.util.HashMap;
import java.util.Map;

import org.javatuples.Pair;
import org.shark.miai.common.enums.PlatformEnum;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.constant.Const;

/**
 * 微信支付配置参数
 * @author mouzhanpeng at [2017年10月9日 下午5:55:01]
 * @since JDK 1.8.0
 */
public final class WxpayConfig {
	//读取配置文件
	private static final ServiceConfig CONFIG = ServiceConfig.getInstance(); 
	
	private static class Key{
		private static final String APP_YOYO_ID_INDEX = "wx.yoyo.app.id";
		private static final String APP_TONGCHENG_ID_INDEX = "wx.tongcheng.app.id";
		private static final String APP_YUEAI_ID_INDEX = "wx.yueai.app.id";
		private static final String APP_XIANGYUE_ID_INDEX = "wx.xiangyue.app.id";
		private static final String APP_OUYU_ID_INDEX = "wx.ouyu.app.id";
		private static final String APP_XIANGYU_ID_INDEX = "wx.xiangyu.app.id";
		private static final String APP_YUEJIAN_ID_INDEX = "wx.yuejian.app.id";
		private static final String APP_MILIAO_ID_INDEX = "wx.miliao.app.id";
		private static final String APP_LIAO_ID_INDEX = "wx.liao.app.id";
		
		
		private static final String PARTNER_YOYO_INDEX = "wx.yoyo.partner";
		private static final String PARTNER_TONGCHENG_INDEX = "wx.tongcheng.partner";
		private static final String PARTNER_YUEAI_INDEX = "wx.yueai.partner";
		private static final String PARTNER_XIANGYUE_INDEX = "wx.xiangyue.partner";
		private static final String PARTNER_OUYU_INDEX = "wx.ouyu.partner";
		private static final String PARTNER_XIANGYU_INDEX = "wx.xiangyu.partner";
		private static final String PARTNER_YUEJIAN_INDEX = "wx.yuejian.partner";
		private static final String PARTNER_MILIAO_INDEX = "wx.miliao.partner";
		private static final String PARTNER_LIAO_INDEX = "wx.liao.partner";
		
		
		private static final String PARTNER_KEY_INDEX = "wx.partner.key";
		private static final String PREPAY_URL_INDEX = "wx.prepay.url";
		private static final String NOTIFY_URL_INDEX = "wx.notify.url";
		private static final String TRADE_TYPE_INDEX = "wx.trade.type";
		private static final String PACKAGE_INDEX = "wx.package";
		
		private static final String REDIRECT_URL = "pay.redirect.url";
	}
	
	private static class Value{
		
		private static final String PARTNER_KEY = "kjlFJDU0RF55S29KjIeLFSJmYZlp521o";
		
		private static final String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		private static final String REDIRECT_URL = "http://vapi.yoyo.liaomeivideo.com/vmi-video-service/third/party/pay/query";
		
		private static final String NOTIFY_URL = (Const.is_test ? "http://sandbox.nodata.com" : "http://service.yoyo.liaomeivideo.com/shark-miai-service") + "/third/party/wx/notify";
		
		private static final String TRADE_TYPE = "APP";
		
		private static final String PACKAGE = "Sign=WXPay";

	}
	// 微信开发平台appId,partner合集
	private static final Map<String, Pair<String, String>> APP_IDS = new HashMap<>();
	
	static{
		APP_IDS.put("com.ydwx.yoyo", Pair.with(CONFIG.getString(Key.APP_YOYO_ID_INDEX), CONFIG.getString(Key.PARTNER_YOYO_INDEX)));
		APP_IDS.put("com.ydwx.yoyo2", Pair.with(CONFIG.getString(Key.APP_TONGCHENG_ID_INDEX), CONFIG.getString(Key.PARTNER_TONGCHENG_INDEX)));
		APP_IDS.put("com.ydwx.yoyo3", Pair.with(CONFIG.getString(Key.APP_YUEJIAN_ID_INDEX), CONFIG.getString(Key.PARTNER_YUEJIAN_INDEX)));
		APP_IDS.put("com.tjhj.miliao", Pair.with(CONFIG.getString(Key.APP_MILIAO_ID_INDEX), CONFIG.getString(Key.PARTNER_MILIAO_INDEX)));
		
		APP_IDS.put("com.vmi.noshop", Pair.with(CONFIG.getString(Key.APP_LIAO_ID_INDEX), CONFIG.getString(Key.PARTNER_LIAO_INDEX)));
		APP_IDS.put("com.yoyo.jiaoyouliao", Pair.with(CONFIG.getString(Key.APP_OUYU_ID_INDEX), CONFIG.getString(Key.PARTNER_OUYU_INDEX)));
		APP_IDS.put("com.miliao.miliaoliao", Pair.with(CONFIG.getString(Key.APP_YUEAI_ID_INDEX), CONFIG.getString(Key.PARTNER_YUEAI_INDEX)));
		
		//android添加一个伪支付渠道
		APP_IDS.put("android.pay", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		
		APP_IDS.put("iOS_A1", Pair.with(CONFIG.getString(Key.APP_TONGCHENG_ID_INDEX), CONFIG.getString(Key.PARTNER_TONGCHENG_INDEX)));
		APP_IDS.put("iOS_A2", Pair.with(CONFIG.getString(Key.APP_YUEAI_ID_INDEX), CONFIG.getString(Key.PARTNER_YUEAI_INDEX)));
		APP_IDS.put("iOS_A3", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_A4", Pair.with(CONFIG.getString(Key.APP_OUYU_ID_INDEX), CONFIG.getString(Key.PARTNER_OUYU_INDEX)));
		APP_IDS.put("iOS_A5", Pair.with(CONFIG.getString(Key.APP_XIANGYU_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYU_INDEX)));
		
		//新增ios支付渠道值
		APP_IDS.put("iOS_V0", Pair.with(CONFIG.getString(Key.APP_TONGCHENG_ID_INDEX), CONFIG.getString(Key.PARTNER_TONGCHENG_INDEX)));
		APP_IDS.put("iOS_V1", Pair.with(CONFIG.getString(Key.APP_TONGCHENG_ID_INDEX), CONFIG.getString(Key.PARTNER_TONGCHENG_INDEX)));
		APP_IDS.put("iOS_V6", Pair.with(CONFIG.getString(Key.APP_LIAO_ID_INDEX), CONFIG.getString(Key.PARTNER_LIAO_INDEX)));
		APP_IDS.put("iOS_V7", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V8", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V9", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V10", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V11", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V12", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V13", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V14", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V15", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V16", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V17", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V18", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V19", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("iOS_V20", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		
		//ios企业包新增的渠道
		APP_IDS.put("User_Share_i", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("Zhubo_i", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
		APP_IDS.put("test", Pair.with(CONFIG.getString(Key.APP_XIANGYUE_ID_INDEX), CONFIG.getString(Key.PARTNER_XIANGYUE_INDEX)));
	}
	//PARTNER KEY
	public static final String PARTNER_KEY = CONFIG.getString(Key.PARTNER_KEY_INDEX, Value.PARTNER_KEY);
	
	// 预支付接口
	public static final String PREPAY_URL = CONFIG.getString(Key.PREPAY_URL_INDEX, Value.PREPAY_URL);
	
	// 异步通知地址
	public static final String NOTIFY_URL = CONFIG.getString(Key.NOTIFY_URL_INDEX, Value.NOTIFY_URL);
	
	// 交易类型
	public static final String TRADE_TYPE = CONFIG.getString(Key.TRADE_TYPE_INDEX, Value.TRADE_TYPE);
	
	// 暂定包值
	public static final String PACKAGE = CONFIG.getString(Key.PACKAGE_INDEX, Value.PACKAGE);
	
	//支付完成之后的重定向的地址
	public static final String REDIRECT_URL = CONFIG.getString(Key.REDIRECT_URL, Value.REDIRECT_URL);
	
	// 返回状态成功
	public static final String SUCCESS = "SUCCESS";
	
	// 返回状态失败
	public static final String FAIL = "FAIL";
	
	/**
	 * 获取微信appId
	 * @param platform
	 * @param channel
	 * @return
	 */
	public static Pair<String, String> getAppInfo(int platform, String channel, String pkg){
		if(PlatformEnum.android.type == platform){
			return APP_IDS.get(pkg);
		}else{
			return APP_IDS.get(channel);
		}
	}
	
	private WxpayConfig(){
	}

}
