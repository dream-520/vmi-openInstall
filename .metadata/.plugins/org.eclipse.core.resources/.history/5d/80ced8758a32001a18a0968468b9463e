package com.tigerjoys.shark.miai.utils;

import java.util.Map;
import java.util.TreeMap;

import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;

public class SignUtil {

    public static String getSign(Map<String, Object> data, String secret) {
        if (data == null) {
            return null;
        }
        //排序参数
        Map<String, Object> mappingList = new TreeMap<>(data);
        StringBuilder plainText= new StringBuilder();
        mappingList.forEach((k, v) -> {
            if (!"sign".equals(k) && Tools.isNotNull(v)) {
                plainText.append(String.format("%s=%s&", k, v));
            }
        });
        String substring = plainText.substring(0, plainText.length() - 1);
        return MD5.encode(substring + secret);
    }
	
}
