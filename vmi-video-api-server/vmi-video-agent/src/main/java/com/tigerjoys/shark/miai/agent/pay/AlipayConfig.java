package com.tigerjoys.shark.miai.agent.pay;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.constant.Const;

/**
 * 支付宝配置参数
 * 
 * @author mouzhanpeng at [2017年10月9日 下午5:50:31]
 * @since JDK 1.8.0
 */
public final class AlipayConfig {
	// 读取配置文件
	private static final ServiceConfig CONFIG = ServiceConfig.getInstance();

	private static class Key {
		private static final String PARTNER_INDEX = "ali.partner";
		private static final String PRIVATE_KEY_INDEX = "ali.private.key";
		private static final String ALIPAY_PUBLIC_KEY_INDEX = "ali.public.key";
		private static final String SIGN_TYPE_INDEX = "ali.sign.type";
		private static final String NOTIFY_URL_INDEX = "ali.notify.url";
		private static final String INPUT_CHARSET_INDEX = "ali.input.charset";
		private static final String PAYMENT_TYPE_INDEX = "ali.payment.type";
		private static final String SERVICE_INDEX = "ali.service";
	}

	private static class Value {

		private static final String PARTNER = "2088521239958075";

		private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJuIiNyLClMi0+WiBLPmR+cR8Ys2hb7YytRjtrymgAy+7I1dSu+JIGNolXxBkB5mMYxh4z8dbofnYGJLuoTKfq34N1tGHZOk+vPYaUyQtOkkCnfsOcNY9tpaCKTwqDWiSnT6Ittjg7E5GZc6k6xMBFuZbkpdIwpajHyORJYbkhkZAgMBAAECgYAk1awcFy3FzKzEaJ+Wf4Yt2lkLMElyjmNS856WhRQ0cI8TV7HkkuRBxsYIXHqmSGuZpKEuFGlV9F0mSmsNAewIJbVdd+Ag73yu3k/TQnCG8U4L3M8Ge3K5a1esDzfWfMJANquAnaW40zZtaIXc+kz/eRSFHXPnPEcbjyGABmP0pQJBAOv0BS5TKvdLQ4wtv+w5VEmvycRTnuMZZW3moTfc5dzXrpSZAPjEObJc8YAeJGXzFsIKkuqG7gQ8/Ezhp0DQ/TsCQQCov2IUdy80N0L+WqF4RDSdT5f9mCN2hXsn69vbslyC2cutCzFWNSRqTCZIHqZcKdC9hwKTy4Ta8qkB80zv1W27AkEAipuvy3XZ4KnbBWb+vqMy65KToXxJ8zjBB92ayXk/owskqHB1TK3hAvFsgQmz8hvqO5UAlzsqaVz2Daflm8LpbwJAIUxCik9KXT9aVN6FZjnvN+twznWq6cRFHQeJhryXTKq1ahv/TEeIPAFyvGBv61fE/OEbPRa3zIx9lQjxcZHn6QJAZXiniXzBqn4x1DxsaMyuvoOAZA6HQ9GXlIr0C848dPhoEzh4Vu8Q6ZrefSlLt4ix3RR7N7HgxHPLGO4FT4nW6w==";

		private static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

		private static final String SIGN_TYPE = "RSA";

		private static final String NOTIFY_URL = (Const.is_test ? "http://sandbox.nodata.com" : "http://service.yoyo.liaomeivideo.com/shark-miai-service") + "/third/party/ali/notify";

		private static final String INPUT_CHARSET = "utf-8";

		private static final String PAYMENT_TYPE = "1";

		private static final String SERVICE = "mobile.securitypay.pay";

	}

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static final String PARTNER = CONFIG.getString(Key.PARTNER_INDEX, Value.PARTNER);

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static final String SELLER_ID = PARTNER;

	// 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static final String PRIVATE_KEY = CONFIG.getString(Key.PRIVATE_KEY_INDEX, Value.PRIVATE_KEY);

	// 支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static final String ALIPAY_PUBLIC_KEY = CONFIG.getString(Key.ALIPAY_PUBLIC_KEY_INDEX, Value.ALIPAY_PUBLIC_KEY);

	// 签名方式
	public static final String SIGN_TYPE = CONFIG.getString(Key.SIGN_TYPE_INDEX, Value.SIGN_TYPE);

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static final String NOTIFY_URL = CONFIG.getString(Key.NOTIFY_URL_INDEX, Value.NOTIFY_URL);

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static final String INPUT_CHARSET = CONFIG.getString(Key.INPUT_CHARSET_INDEX, Value.INPUT_CHARSET);

	// 支付类型 ，无需修改
	public static final String PAYMENT_TYPE = CONFIG.getString(Key.PAYMENT_TYPE_INDEX, Value.PAYMENT_TYPE);

	// 接收通知的接口名
	public static final String SERVICE = CONFIG.getString(Key.SERVICE_INDEX, Value.SERVICE);

	// 支付完成
	public static final String TRADE_FINISHED = "TRADE_FINISHED";

	// 支付成功
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";

	private AlipayConfig() {
	}
}
