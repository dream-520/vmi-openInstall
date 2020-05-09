/**
 * 
 */
package com.tigerjoys.shark.miai.agent.pay;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.constant.Const;

/**
 * ClassName: IapConfig <br/>
 * date: 2017年7月25日 下午2:51:10 <br/>
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public final class IapConfig {
	//读取配置文件
	private static final ServiceConfig CONFIG = ServiceConfig.getInstance(); 
	
	private static class Key{
		private static final String DEV_VERIFY_URL_INDEX = "iap.dev.verify.url";
		private static final String PROD_VERIFY_URL_INDEX = "iap.prod.verify.url";
		private static final String VERIFY_URL_INDEX = "iap.verify.url";
		private static final String BUNDLE_ID_INDEX = "iap.bundle.id";
		private static final String REQUEST_CONTENT_KEY_INDEX = "iap.request.content.key";
		private static final String REQUEST_CONTENT_PASSWORD_INDEX = "iap.request.content.password";
	}
	
	private static class Value{
		public static final String DEV_VERIFY_URL = "https://sandbox.itunes.apple.com/verifyReceipt";
		
		public static final String PROD_VERIFY_URL = "https://buy.itunes.apple.com/verifyReceipt";
		
		public static final String VERIFY_URL = Const.is_test ? DEV_VERIFY_URL : PROD_VERIFY_URL;
		
		public static final String BUNDLE_ID = "app.yoyo.ios";
		
		public static final String REQUEST_CONTENT_KEY = "receipt-data";
		public static final String REQUEST_CONTENT_PASSWORD = "3bdfb08dc3544a709dd24e1f6ffd2c68";
	}
	
	//沙盒地址
	public static final String DEV_VERIFY_URL = CONFIG.getString(Key.DEV_VERIFY_URL_INDEX, Value.DEV_VERIFY_URL);
	
	//线上地址
	public static final String PROD_VERIFY_URL = CONFIG.getString(Key.PROD_VERIFY_URL_INDEX, Value.PROD_VERIFY_URL);
	
	// 购买凭证验证地址
	public static final String VERIFY_URL = CONFIG.getString(Key.VERIFY_URL_INDEX, Value.VERIFY_URL);
	
	// APP 注册绑定标识
	public static final String BUNDLE_ID = CONFIG.getString(Key.BUNDLE_ID_INDEX, Value.BUNDLE_ID);
	
	// 请求苹果服务器参数键值
	public static final String REQUEST_CONTENT_KEY = CONFIG.getString(Key.REQUEST_CONTENT_KEY_INDEX, Value.REQUEST_CONTENT_KEY);
	
	// 请求苹果服务器参数 键值   共享密钥
	public static final String REQUEST_CONTENT_PASSWORD = CONFIG.getString(Key.REQUEST_CONTENT_PASSWORD_INDEX, Value.REQUEST_CONTENT_PASSWORD);

	private IapConfig() {
	}
}
