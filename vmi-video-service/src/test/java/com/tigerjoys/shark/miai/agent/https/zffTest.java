package com.tigerjoys.shark.miai.agent.https;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;

public class zffTest {
	
	@Test
	public void testzfb(){
		HashMap<String, String>hsmp = new HashMap<String, String>();
		hsmp.put("requestType", "SafePay");
		hsmp.put("fromAppUrlScheme", "alipays");
		hsmp.put("dataString", "h5_route_token=\"RZ251JXJQpraej39xqVWEbcLRApiVSmobilecashierRZ25\"&is_h5_route=\"true\"");
		String json = "alipay://alipayclient/?"+encodeURIComponent(JsonHelper.toJson(hsmp));
		System.out.println(json);
	}

	public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

	public static String encodeURIComponent(String input) {

		if (input == null || "".equals(input)) {

			return input;

		}

 

		int l = input.length();

		StringBuilder o = new StringBuilder(l * 3);

		try {

			for (int i = 0; i < l; i++) {

				String e = input.substring(i, i + 1);

				if (ALLOWED_CHARS.indexOf(e) == -1) {

					byte[] b = e.getBytes("utf-8");

					o.append(getHex(b));

					continue;

				}

				o.append(e);

			}

			return o.toString();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return input;

	}
	
	private static String getHex(byte buf[]) {

		StringBuilder o = new StringBuilder(buf.length * 3);

		for (int i = 0; i < buf.length; i++) {

			int n = (int) buf[i] & 0xff;

			o.append("%");

			if (n < 0x10) {

				o.append("0");

			}

			o.append(Long.toString(n, 16).toUpperCase());

		}

		return o.toString();

	}
	

}



