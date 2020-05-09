/**
 * 
 */
package com.tigerjoys.shark.miai.agent.utils;

import java.security.MessageDigest;

/**
 * ClassName: CheckSumBuilder <br/>
 * date: 2017年7月26日 上午11:18:33 <br/>
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public class CheckSumBuilder {

	/**
	 * 校验和
	 * 
	 * @param appSecret
	 * @param nonce
	 * @param curTime
	 * @return
	 */
	public static String getCheckSum(String appSecret, String nonce, String curTime) {
		return encode("sha1", appSecret + nonce + curTime);
	}

	/**
	 * md5
	 * 
	 * @param requestBody
	 * @return
	 */
	public static String getMD5(String requestBody) {
		return encode("md5", requestBody);
	}

	private static String encode(String algorithm, String value) {
		if (value == null) {
			return "";
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(value.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };
}
