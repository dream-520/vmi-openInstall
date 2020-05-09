package org.shark.miai.common.util;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;

/**
 * AES数据库加密帮助类
 * @author chengang
 *
 */
public final class AESFieldUtils {
	
	/**
	 * 加密密钥
	 */
	private static final String SECRET_KEY = "$0$TIGER#1#!2@3@";
	
	/**
	 * 加密字符串
	 * @param s - String
	 * @return String
	 * @throws Exception
	 */
	public static String encrypt(String s) throws Exception {
		if (Tools.isNull(s)) return null;
		return AESCipher.aesEncryptString(s , SECRET_KEY);
	}
	
	/**
	 * 解密字符串
	 * @param s - String
	 * @return String
	 * @throws Exception
	 */
	public static String decrypt(String s) throws Exception {
		if (Tools.isNull(s)) return null;
		return AESCipher.aesDecryptString(s , SECRET_KEY);
	}

}
