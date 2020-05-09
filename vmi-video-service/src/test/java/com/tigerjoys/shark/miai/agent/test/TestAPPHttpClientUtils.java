package com.tigerjoys.shark.miai.agent.test;



import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.context.RequestHeader;


public class TestAPPHttpClientUtils {
  private final static Logger LOGGER = LoggerFactory.getLogger(TestAPPHttpClientUtils.class);

  private final static String UTF8 = "UTF-8";

  public static CloseableHttpClient httpclient = null;

  static {
    // 初始化线程池
    RequestConfig params = RequestConfig.custom()
    		.setConnectTimeout(3000)
    		.setConnectionRequestTimeout(1000)
    		.setSocketTimeout(4000)
    		.setExpectContinueEnabled(true).build();

    PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager();
    pccm.setMaxTotal(300); // 连接池最大并发连接数
    pccm.setDefaultMaxPerRoute(50); // 单路由最大并发数

    HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
      public boolean retryRequest(IOException exception , int executionCount , HttpContext context) {
        // 重试1次,从1开始
        if (executionCount > 1) {
          return false;
        }
        if (exception instanceof NoHttpResponseException) {
          LOGGER.info(
              "[NoHttpResponseException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
          return true;
        }
        else if (exception instanceof SocketException) {
          LOGGER.info("[SocketException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
          return true;
        }
        return false;
      }
    };
    httpclient = HttpClients.custom().setConnectionManager(pccm).setDefaultRequestConfig(params).setRetryHandler(retryHandler)
        .build();
  }

  public static String doPost(RequestHeader header,String urlToRequest , Map<String, Object> parameters){
	  return doPostJson(header,urlToRequest,parameters,null,null,null);
  } 

  
  public static String doPostJson(RequestHeader header,String urlToRequest , Map<String, Object> parameters , Integer connectionRequestTimeout ,
      Integer connectTimeout , Integer socketTimeout) {

    Long startTs = System.currentTimeMillis();
    List<NameValuePair> nvps = new ArrayList<NameValuePair>();

    if (parameters != null && !parameters.isEmpty()) {
      for (Entry<String, Object> entry : parameters.entrySet()) {
        nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
      }
    }

    try {
      LOGGER.info("post-req:url:{},param:{}", urlToRequest, JSON.toJSONString(parameters));
    
      String encryptCode = JsonHelper.toJson(header);
      String encryp=AESCipher.aesEncryptString(encryptCode);
      HttpPost post = new HttpPost(urlToRequest);
      post.setHeader("Content-Type",Produce.TEXT_ENCODE);
      post.setHeader("header-encrypt-code", encryp);
      if (connectionRequestTimeout != null && connectTimeout != null && socketTimeout != null) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
            .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
        post.setConfig(requestConfig);
      }
      if(parameters!=null){
    	String json=JsonHelper.toJson(parameters);
    	String jsonEntity=AESCipher.aesEncryptString(json);
      StringEntity entity = new StringEntity(jsonEntity,"utf-8");
      post.setEntity(entity);
      }
      
      String result = invoke(post);
      Long endTs = System.currentTimeMillis();
      Long currentMethodCallTime = endTs - startTs;
      if (currentMethodCallTime > 5000) {
        LOGGER.warn("url:{},call time {} ms", urlToRequest, currentMethodCallTime);
        LOGGER.info("所有存活线程="+Thread.getAllStackTraces().size());
      }
      LOGGER.info("post-rps:{}", result);
      result=AESCipher.aesDecryptString(result);
      return result;
    }catch (Exception e) {
      LOGGER.error("[HttpClientUtils][post][Unsupported Encoding Exception]", e);
    }
    return null;
  }

  
 
  
  private static String invoke(HttpUriRequest request) {
    CloseableHttpResponse response = null;
    try {
      response = httpclient.execute(request);
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, UTF8);
      }
    }
    catch (IOException e) {
      LOGGER.error(
          "[HttpClientUtils][invoke][method:" + request.getMethod() + " URI:" + request.getURI() + "] is request exception", e);
    }
    finally {
      if (response != null) {
        try {
          response.close();
        }
        catch (IOException e) {
          LOGGER.error(
              "[HttpClientUtils][invoke][method:" + request.getMethod() + " URI:" + request.getURI() + "] is closed exception",
              e);
        }
      }
    }
    return null;
  }
  
  
  
  public static void main(String[] args) {
	  //手机报文头信息
	  RequestHeader header=new  RequestHeader();
	  header.setClientId("dsfsdsdfdasfdasfdsf");
	  header.setUserid(0L);
	  header.setMobile("18811511130");
	  //传的参数
	  HashMap<String, Object>hsmp=new HashMap<>();
	  hsmp.put("stamp", "");
	  hsmp.put("page", 1);
	  hsmp.put("pageSize", 10);
	  String res=TestAPPHttpClientUtils.doPost(header,"http://127.0.0.1:8080/api/userwatch/anchorDynamic", hsmp);
	  System.out.println("res="+res);
}
  
}
