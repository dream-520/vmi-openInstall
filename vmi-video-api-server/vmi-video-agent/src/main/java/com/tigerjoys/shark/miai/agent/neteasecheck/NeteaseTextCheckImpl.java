package com.tigerjoys.shark.miai.agent.neteasecheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;

@Service
public class NeteaseTextCheckImpl implements INeteaseTextCheck {

	/**
	 * 读取classpath下properties文件的信息单例类
	 */
	private static ServiceConfig serviceConfig = ServiceConfig.getInstance();
	 /**
     * 产品密钥ID，产品标识
     */
    private final static String SECRETID = serviceConfig.getString("netease_check_SECRETID" , "");
    /**
     * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     */
    private final static String SECRETKEY = serviceConfig.getString("netease_check_SECRETKEY" , "");
    /**
     * 业务ID，易盾根据产品业务特点分配
     */
    private final static String BUSINESSID = serviceConfig.getString("netease_check_BUSINESSID" , "");
    /**
     * 易盾内容安全文本在线检测接口地址 
     */
    private final static String API_URL = "https://as.dun.163yun.com/v3/text/check";
    /**
     * 实例化HttpClient，发送http请求使用，可根据需要自行调参
     */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 10000, 4000, 4000);
    
    private  final Logger logger = LoggerFactory.getLogger(getClass());
	
   
    @Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis cacheRedis; 
    
	@Override
	public String check(long oriderid,String chatText) throws Exception {
		Map<String, String> params = new HashMap<>();
	       // 1.设置公共参数
        params.put("secretId", SECRETID);
        params.put("businessId", BUSINESSID);
        params.put("version", "v3.1");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(new Random().nextInt()));
        
        logger.info("########:"+BUSINESSID);
        
        // 2.设置私有参数
        params.put("dataId", oriderid+"");
        params.put("content", chatText);
        // params.put("dataType", "1");
        // params.put("ip", "123.115.77.137");
        // params.put("account", "java@163.com");
        // params.put("deviceType", "4");
        // params.put("deviceId", "92B1E5AA-4C3D-4565-A8C2-86E297055088");
        // params.put("callback", "ebfcad1c-dba1-490c-b4de-e784c2691768");
        // params.put("publishTime", String.valueOf(System.currentTimeMillis()));
        
        String signature = SignatureUtils.genSignature(SECRETKEY, params);
        params.put("signature", signature);
        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        return HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);
	}

}
