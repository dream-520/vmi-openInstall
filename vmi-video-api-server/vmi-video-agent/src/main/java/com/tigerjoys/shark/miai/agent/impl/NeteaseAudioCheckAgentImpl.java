package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.INeteaseAudioCheckAgent;
import com.tigerjoys.shark.miai.agent.neteasecheck.HttpClient4Utils;
import com.tigerjoys.shark.miai.agent.neteasecheck.SignatureUtils;

@Service
public class NeteaseAudioCheckAgentImpl implements INeteaseAudioCheckAgent {

	private final static String SECRETID = "76c0f34964b66e731362aab85e9185d0";
    /** 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露 */
    private final static String SECRETKEY = "183ee30d37ab4a45773f7b0d87c45cbd";
    /** 业务ID，易盾根据产品业务特点分配 */
    private final static String BUSINESSID = "b158f9c6f3bd94aa92b6895ca137c7cf";
    /** 易盾反垃圾云服务文本在线检测接口地址 */
    private final static String API_URL = "https://as.dun.163yun.com/v3/audio/submit";
    /** 实例化HttpClient，发送http请求使用，可根据需要自行调参 */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 2000, 2000, 2000);
    
	@Override
	public Map<String, String> check(String url, String callbackUrl) throws Exception {
		Map<String, String> data = new HashMap<>();
		if(Tools.isNotNull(url)) {
			Map<String, String> params = new HashMap<String, String>();
	        // 1.设置公共参数
	        params.put("secretId", SECRETID);
	        params.put("businessId", BUSINESSID);
	        params.put("version", "v3");
	        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
	        params.put("nonce", String.valueOf(new Random().nextInt()));

	        // 2.设置私有参数
	        params.put("url", url);
	        
	        // 3.设置回调地址
	        params.put("callbackUrl", callbackUrl);
	        
	        // 4.生成签名信息
	        String signature = SignatureUtils.genSignature(SECRETKEY, params);
	        params.put("signature", signature);

	        // 5.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
	        String response = HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);

	        // 6.解析接口返回值
	        JSONObject object = JsonHelper.toJsonObject(response);
	        if(Tools.isNotNull(object)) {
	        	int code = object.getIntValue("code");
	        	if(code == 200) {
	        		JSONObject result = object.getJSONObject("result");
	        		if(Tools.isNotNull(result)) {
	        			String taskId = result.getString("taskId");
	        			int status = result.getIntValue("status");
	        			data.put("taskId", taskId);
	        			data.put("status", status+"");
	        			System.out.println(String.format("ERROR: code=%s, taskId=%s, status=%s", code, taskId, status));
	        		}
	        	}
	        }
		}	
		return data;
	}

}
