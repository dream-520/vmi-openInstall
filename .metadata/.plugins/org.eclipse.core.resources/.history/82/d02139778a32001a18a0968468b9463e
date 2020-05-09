package com.tigerjoys.shark.miai.agent.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试进行加解密处理
 * @author shiming
 *
 */
public class EncodeTest extends BaseTestConfig {

	@Test
	public void testDecode() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		//String encrypt = "VQC2bxiq5qVv0na49U+RFiCIjcfaW197Sqay2osy3DIhjHtholeGCs/0gLjEYLJG78dcuxH2ZwKGTkJ9GQf0KRGGvrScxZGQYQQS4G7/7IfUUTHs6avLwlmARA4qr18pAJJ85qYUekmsI/0N0QyuNzOU+qcBcee7JvKM5FxOXBCxWTq6Z0ijtAjh6efavzhTp/ft790XgGHJzm/QBFw7XEyX3xvqPxhHDmuUGgRBK8lU4D9tSE4M5/lce32l/vKgmnhFpvSkRnvGydDiaTx3Z3e40X+/qRLGwXhX8EjFN05Fgg7WxR5ebjHmoAeddb6wWO2FT9UiH66kCOkOLgKYGNvCRSxayqRY7SYCi04bTN8A0zOjrsr4HIs5gPq4MH9APSzDrW12zKfBuPQx83qrUqmQni4FTmEYOIGapzP5nSN3tutWDoBR1utcYPtmTCduzVkhm2pDarTMZfkYwP0Wx0Xrh0sJ9TcfhgkStcJiRxc=";
		String encrypt = "a7eWNJep73FVxT2BCUlRog==";
		
		//String encrypt = "+/11BgNgPOt9EfpquHKDWLsrWGgFFuvmhyvpu6Y+OIj14JZCh9aDf7MSK55ZHHnKGghR7DFMzWfGCafvc/ahOnGLdmQBbl3yPJlNnm5iLPPomvmkYTpaXUL9i8mY5W1kJ4CtFejosbxEvGkWd6FarXq0AdFyZANgs6gBmwYwlwKxhObS6rIug639SDsswsqBd1XjlP5HVmGz2wDuHIpflpVypmBS1fYgqnIedy4oXZF3Vwb60DGs/2RdTzwg92iS";
		//String decodeValues = AESCipher.aesDecryptString(encrypt);
		//System.err.println(decodeValues);
		
		String code = "18942097670";
		String encode = AESCipher.aesEncryptString(code);
		System.err.println(encode);
	}
}
