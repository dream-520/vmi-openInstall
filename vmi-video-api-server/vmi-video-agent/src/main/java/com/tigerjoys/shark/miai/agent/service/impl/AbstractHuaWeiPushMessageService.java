package com.tigerjoys.shark.miai.agent.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.service.IHuaWeiPushMessageService;

/**
 * 文档地址
 * https://developer.huawei.com/consumer/cn/service/hms/catalog/huaweipush_agent.html
 * @author shiming
 */
public abstract class AbstractHuaWeiPushMessageService implements IHuaWeiPushMessageService {

	private String apiUrl = "https://api.push.hicloud.com/pushsend.do";
	private String tokenUrl = "https://login.vmall.com/oauth2/token";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.SYS_CONFIG_CACHE)
	private CacheRedis cacheRedis;
	
    //发送Push消息
	@Override
    public void sendPushMessage(String token, PushMessageDto dto) throws IOException {      
		
		String accessToken = getAccessToken(getAppSecret(), getAppId());
		if(Tools.isNull(accessToken)) {
			logger.error("华为推送获取token出现了问题");
			return ;
		}
        /*PushManager.requestToken为客户端申请token的方法，可以调用多次以防止申请token失败*/
        /*PushToken不支持手动编写，需使用客户端的onToken方法获取*/
        JSONArray deviceTokens = new JSONArray();//目标设备Token
        deviceTokens.add(token);
          
        JSONObject body = new JSONObject();//仅通知栏消息需要设置标题和内容，透传消息key和value为用户自定义
        body.put("title", dto.getTitle());//消息标题
        body.put("content", dto.getContent());//消息内容体
        
        JSONObject param = new JSONObject();
        JSONObject action = new JSONObject();
        JSONObject msg = new JSONObject();
        JSONObject hps = new JSONObject();//华为PUSH消息总结构体
        //最终的消息实体消息
        JSONObject payload = new JSONObject();
        //检测是否是外部游览器要打开的页面
        if(dto.getMsgType() == PushTypeEnum.type_web_H5.getCode()) {
        	param.put("url", dto.getUrl());//定义需要打开的appPkgName
        	
        	action.put("type", 2);//类型2为打开指定的页面
            action.put("param", param);//消息点击动作参数
            
            msg.put("type", 3);//3: 通知栏消息，异步透传消息请根据接口文档设置
            msg.put("action", action);//消息点击动作
            msg.put("body", body);//通知栏消息body内容
            
            hps.put("msg", msg);
        } else {
        	param.put("appPkgName", dto.getPackageName());//定义需要打开的appPkgName
        	
        	action.put("type", 3);//类型3为打开APP，其他行为请参考接口文档设置
            action.put("param", param);//消息点击动作参数
            
            msg.put("type", 3);//3: 通知栏消息，异步透传消息请根据接口文档设置
            msg.put("action", action);//消息点击动作
            msg.put("body", body);//通知栏消息body内容
            
            //添加对应的发送的自定义消息的内容
            JSONObject pushMsg = new JSONObject();
            pushMsg.put("pushMsg", JsonHelper.toJson(dto));
            JSONArray customize = new JSONArray();
            customize.add(pushMsg);
            
            JSONObject ext = new JSONObject();//扩展信息，含BI消息统计，特定展示风格，消息折叠。
            //ext.put("biTag", "Trump");//设置消息标签，如果带了这个标签，会在回执中推送给CP用于检测某种类型消息的到达率和状态
            //ext.put("icon", "http://pic.qiantucdn.com/58pic/12/38/18/13758PIC4GV.jpg");//自定义推送消息在通知栏的图标,value为一个公网可以访问的URL
            ext.put("customize", customize);
            
            hps.put("msg", msg);
            hps.put("ext", ext);
		}
        
        payload.put("hps", hps);
        logger.info(JsonHelper.toJson(dto));
        logger.info(payload.toJSONString());
        String postBody = MessageFormat.format(
        	"access_token={0}&nsp_svc={1}&nsp_ts={2}&device_token_list={3}&payload={4}",
            URLEncoder.encode(getAccessToken(getAppSecret(), getAppId()),"UTF-8"),
            URLEncoder.encode("openpush.message.api.send","UTF-8"),
            URLEncoder.encode(String.valueOf(System.currentTimeMillis() / 1000),"UTF-8"),
            URLEncoder.encode(deviceTokens.toString(),"UTF-8"),
            URLEncoder.encode(payload.toString(),"UTF-8"));
        
        String postUrl = apiUrl + "?nsp_ctx=" + URLEncoder.encode("{\"ver\":\"1\", \"appId\":\"" + getAppId() + "\"}", "UTF-8");
        String result = httpPost(postUrl, postBody, 5000, 5000);
        logger.info("推送的华为设备标识:"+token +"推送结果:"+result);
    }
    
    @Override
	public String getAccessToken(String appSecret, String appid) {
		//return null;
    	//根据对应的appid在缓存中取得对应的认证token值
    	String key = "huawei_push_" + appid;
    	String token = cacheRedis.get(key);
    	return token;
	}
    
    public void refreshToken() throws IOException {
    	//首先检测对应的认证token是否过期      注意没二十分钟获取一次    提前2个小时过期  避免中间出现问题导致token值出现错误 
    	String key = "huawei_push_" + getAppId();
    	long expire = cacheRedis.ttl(key);
    	if(expire <= 2 * 60 * 60) {
    		//触发获取新的认证token值
    		 String msgBody = MessageFormat.format("grant_type=client_credentials&client_secret={0}&client_id={1}", URLEncoder.encode(getAppSecret(), "UTF-8"), getAppId());
    	     String response = httpPost(tokenUrl, msgBody, 5000, 5000);
    	     JSONObject obj = JSONObject.parseObject(response);
    	     if(Tools.isNotNull(obj)) {
    	    	 String accessToken = obj.getString("access_token");
    	         long tokenExpiredTime = obj.getLong("expires_in");
    	         if(Tools.isNotNull(accessToken) && tokenExpiredTime > 0) {
    	        	 logger.error("token:"+accessToken+" tokenExpiredTime:"+tokenExpiredTime);
    	        	 cacheRedis.set(key, accessToken);
    	        	 cacheRedis.expire(key, (int)tokenExpiredTime);
    	         } else {
					logger.error("获取华为accessToken出现了错误:");
				}
    	     }
    	} else {
			logger.info("当前的accessToken未过期   剩余时间为:"+expire);
		}
    }
	
    public String httpPost(String httpUrl, String data, int connectTimeout, int readTimeout) throws IOException {
        OutputStream outPut = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        try {
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection)url.openConnection();          
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.setConnectTimeout(connectTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.connect();
            //POST data
            outPut = urlConnection.getOutputStream();
            outPut.write(data.getBytes("UTF-8"));
            outPut.flush();
            
            // read response
            if (urlConnection.getResponseCode() < 400) {
                in = urlConnection.getInputStream();
            } else {
                in = urlConnection.getErrorStream();
            }
            List<String> lines = IOUtils.readLines(in, urlConnection.getContentEncoding());
            StringBuffer strBuf = new StringBuffer();
            for (String line : lines) {
                strBuf.append(line);
            }
            //System.out.println(strBuf.toString());
            return strBuf.toString();
        } catch (Exception e) {
        	return "error";
		} finally {
            IOUtils.closeQuietly(outPut);
            IOUtils.closeQuietly(in);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    
}
