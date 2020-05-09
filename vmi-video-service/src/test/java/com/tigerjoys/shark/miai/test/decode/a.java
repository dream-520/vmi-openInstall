package com.tigerjoys.shark.miai.test.decode;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class a {
	
    public static String a(String str) throws Exception {
        return a(str, "0102030405060708");
    }

    public static String a(String str, String str2) throws Exception {
    	byte[] contentBytes = str.getBytes("");
		byte[] keyBytes = str2.getBytes("UTF-8");
		byte[] encryptedBytes = a(str.getBytes("UTF-8"), str2.getBytes("UTF-8"));
		Encoder encoder = Base64.getEncoder();
	    return encoder.encodeToString(encryptedBytes);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        return a(bArr, bArr2, 1);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, int i) throws Exception, Exception {
        Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes("UTF-8"));
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr);
    }

    public static String b(String str) throws UnsupportedEncodingException, Exception {
        return b(str, "0102030405060708");
    }

    public static String b(String str, String str2) throws UnsupportedEncodingException, Exception {
    	Decoder decoder = Base64.getDecoder();
	    byte[] encryptedBytes = decoder.decode(str);
	    byte[] keyBytes = str2.getBytes("UTF-8");
        return new String(b(encryptedBytes, str2.getBytes("UTF-8")), "UTF-8");
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        return a(bArr, bArr2, 2);
    }
}