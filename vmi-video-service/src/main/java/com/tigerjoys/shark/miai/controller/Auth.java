package com.tigerjoys.shark.miai.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.utils.DES;
import com.tigerjoys.shark.miai.utils.SignUtil;

import okhttp3.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.util.Base64Utils;

public class Auth {

    private static String appkey = "2db2a98a18128";
    private static String appSecret = "9ea05374746c988ab3373112b2ba9289";
    private static String token = "135042442546438144";
    private static String opToken = "STsid0000001578468631681qMwOYfwxvSwUl3wwPC3Ppn8XK82iJ7r4";
    private static String operator = "CMCC";

    public static void main(String[] args) throws Exception {
        String authHost = "http://identify.verify.mob.com/";
        String url = authHost + "auth/auth/sdkClientFreeLogin";
        HashMap<String, Object> request = new HashMap<>();
        request.put("appkey", appkey);
        request.put("token", token);
        request.put("opToken", opToken);
        request.put("operator", operator);
        request.put("timestamp", System.currentTimeMillis());
        request.put("sign", SignUtil.getSign(request, appSecret));
        String response = postRequestNoSecurity(url, null, request);

        JSONObject jsonObject = JSONObject.parseObject(response);
        System.out.println(jsonObject);
        if (200 == jsonObject.getInteger("status")) {
            String res = jsonObject.getString("res");
            byte[] decode = DES.decode(Base64Utils.decode(res.getBytes()), appSecret.getBytes());
            JSONObject json = JSONObject.parseObject(new String(decode));
            System.out.println(json.getString("res"));
        }
        System.out.println(jsonObject);
    }


    public static String postRequestNoSecurity(String url, Map<String, String> headers, Object data) throws Exception {
        String securityReq = JSON.toJSONString(data);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), securityReq);
        Request.Builder builder = new Request.Builder();
        if (Tools.isNotNull(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        final Request request = builder.addHeader("Content-Length", String.valueOf(securityReq.length()))
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        String securityRes = response.body().string();
        return securityRes;
    }
}
