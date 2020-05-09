package com.tigerjoys.shark.miai.agent.test;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import com.tigerjoys.nbs.common.utils.JsonHelper;

/**
 * 进行md5加密处理
 * @author shiming
 *
 */
public class MD5 {
	
	public static final int NORMAL_MODE_ALPHA = 255;
	
	public static String md5(String code) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(code.getBytes()));
        } catch (Exception exception) {
        	exception.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder encode = new StringBuilder();
        //int i=0;
        for (byte b : bArr) {
            String hex = Integer.toHexString(b & NORMAL_MODE_ALPHA);
            //System.err.println("i 位置:"+i+": "+hex);
            if (hex.length() == 1) {
            	encode.append(0);
            }
            encode.append(hex);
            //i++;
        }
        //System.err.println(encode.toString());
        return encode.toString();
    }
    
	public static void main(String[] args) {
	

		String ss=new String("aaa");
		List<String> list= new ArrayList<>();
		list.add(ss);
		String bb=new String("bbb");
		list.add(bb);
		
		list.remove("aa");
		System.out.println(JsonHelper.toJson(list));
		
		
		System.out.println("max:"+(Long.MAX_VALUE-3));
		
	}
    
}
