package com.tigerjoys.shark.miai.agent.pay;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.constant.Const;

/**
 * 支付中心配置文件
 * @author chengang
 *
 */
public final class PayCenterConfig {
	
	// 读取配置文件
	private static final ServiceConfig CONFIG = ServiceConfig.getInstance();

	private static class Key {
		private static final String PARTNER_INDEX = "pay.center.partner";
		private static final String APPID_INDEX = "pay.center.appid";
		private static final String SECRET_INDEX = "pay.center.secret";
		private static final String CENTER_URL_INDEX = "pay.center.url";
		private static final String NOTIFY_URL_INDEX = "pay.center.notify.url";
		private static final String RETURN_URL_INDEX = "pay.center.return.url";
	}

	private static class Value {

		private static final String PARTNER = "10000000";

		private static final String APPID = "10000000";

		private static final String SECRET = "U0RF55S29KjIeLFSJmYZlp521o";
		
		private static final String CENTER_URL = (Const.is_test ? "http://sandbox.nodata.com" : "http://service.yoyo.liaomeivideo.com/shark-miai-service");

		private static final String NOTIFY_URL = "http://127.0.0.1:8090/third/party/paycenter/notify";

		private static final String RETURN_URL = "http://127.0.0.1:8090/third/party/paycenter/returnNotify";

	}

	/**
	 * 商户ID
	 */
	public static final String PARTNER = CONFIG.getString(Key.PARTNER_INDEX, Value.PARTNER);

	/**
	 * APP ID
	 */
	public static final String APPID = CONFIG.getString(Key.APPID_INDEX, Value.APPID);
	
	/**
	 * 密钥
	 */
	public static final String SECRET = CONFIG.getString(Key.SECRET_INDEX, Value.SECRET);
	
	/**
	 * 支付中心URL
	 */
	public static final String CENTER_URL = CONFIG.getString(Key.CENTER_URL_INDEX, Value.CENTER_URL);
	
	/**
	 * 异步通知URL
	 */
	public static final String NOTIFY_URL =Const.PAY_NOTIFY_SITE + CONFIG.getString(Key.NOTIFY_URL_INDEX, Value.NOTIFY_URL);
	
	/**
	 * 同步回调URL
	 */
	public static final String RETURN_URL =Const.WEB_SITE + CONFIG.getString(Key.RETURN_URL_INDEX, Value.RETURN_URL);
	
	private PayCenterConfig() {
		
	}

}
