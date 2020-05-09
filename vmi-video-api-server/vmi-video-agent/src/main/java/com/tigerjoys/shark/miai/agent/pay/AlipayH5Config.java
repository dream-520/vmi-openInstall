package com.tigerjoys.shark.miai.agent.pay;

import com.tigerjoys.nbs.common.ServiceConfig;

public class AlipayH5Config {
	// 读取配置文件
	private static final ServiceConfig CONFIG = ServiceConfig.getInstance();
	
	private static class Key {
		private static final String APPID = "aliH5.appid";
		private static final String RSA_PRIVATE_KEY = "aliH5.private.key";
		private static final String ALIPAY_PUBLIC_KEY = "aliH5.public.key";
		private static final String NOTIFY_URL = "aliH5.notify.url";
		private static final String RETURN_URL = "aliH5.return.url";
		private static final String SIGNTYPE = "aliH5.signtype";
		private static final String INPUT_CHARSET = "aliH5.input.charset";
	}
	// 支付完成
	public static final String TRADE_FINISHED = "TRADE_FINISHED";
	// 支付成功
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
	
	// 商户appid
	public static String APPID = CONFIG.getString(Key.APPID);
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = CONFIG.getString(Key.RSA_PRIVATE_KEY);
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = CONFIG.getString(Key.ALIPAY_PUBLIC_KEY);
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = CONFIG.getString(Key.NOTIFY_URL);
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = CONFIG.getString(Key.RETURN_URL);
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// RSA2
	public static String SIGNTYPE = CONFIG.getString(Key.SIGNTYPE);
	// 编码
	public static String CHARSET = CONFIG.getString(Key.INPUT_CHARSET);
	// 返回格式
	public static String FORMAT = "json";

	
	
}
