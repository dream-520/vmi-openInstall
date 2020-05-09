package com.tigerjoys.shark.miai.es.configuration;

import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.shark.miai.common.util.Base64Crypto;

/**
 * Java客户端生成工厂
 * elasticsearch util client
 */
public class RestClientFactory{

	private String HOST = "192.168.20.17";
	private int PORT = 9200;
	private String USER = "elastic";
	private String PWD = "Annal7-Astride";
	private String SCHEMA = "http";
	private int CONNECT_TIME_OUT = 5000;
	private int SOCKET_TIME_OUT = 30000;
	private int CONNECTION_REQUEST_TIME_OUT = 500;

    private int MAX_CONNECT_NUM = 100;
    private int MAX_CONNECT_PER_ROUTE = 100;

    private  HttpHost HTTP_HOST  ;
    private  boolean uniqueConnectTimeConfig = false;
    private  boolean uniqueConnectNumConfig = false;
    private  RestClientBuilder builder;
    private  RestClient restClient;
    private  RestHighLevelClient restHighLevelClient;
    
    private  ElasticSearchPoolConfig config;

    public RestClientFactory (ElasticSearchPoolConfig config){
    	this.config = config;
    	init(config);
    }

    public  void init(ElasticSearchPoolConfig config){
    	HOST = config.getHost();
    	PORT = config.getPort();
    	USER = config.getUser();
    	PWD =  config.getPassword();
    	SCHEMA =  config.getSchema();
    	CONNECT_TIME_OUT =  config.getConnectTimeOut();
    	SOCKET_TIME_OUT =  config.getSocketTimeOut();
    	CONNECTION_REQUEST_TIME_OUT =  config.getConnectionRequestTimeOut();
    	MAX_CONNECT_NUM =  config.getMaxConnectNum();
    	MAX_CONNECT_PER_ROUTE =  config.getMaxConnectPerRoute();
    	HTTP_HOST = new HttpHost(HOST,PORT,SCHEMA);
    	
        // 可以初始化多个HttpHost
        builder = RestClient.builder(HTTP_HOST);
     
        String auth = Base64Crypto.encode((USER+":"+PWD).getBytes());
        builder.setDefaultHeaders(new BasicHeader[]{new BasicHeader("Authorization", "Basic " + auth)});
        if(uniqueConnectTimeConfig){
            setConnectTimeOutConfig();
            uniqueConnectTimeConfig = true;
        }
        if(uniqueConnectNumConfig){
            setMultiConnectConfig();
            uniqueConnectNumConfig = true;
        }
        restClient = builder.build();
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    // 主要关于异步httpclient的连接延时配置
    public  void setConnectTimeOutConfig(){
        // requestConfigBuilder
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIME_OUT);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIME_OUT);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
            return requestConfigBuilder;
        });
    }

    /**
     *    主要关于异步httpclient的连接数配置
     */
    public  void setMultiConnectConfig(){
       // setHttpClientConfigCallback
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
            httpClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE);
            return httpClientBuilder;
        });
    }


    public RestClient getClient(){
    	if(restClient == null){
        	init(config);
        }
        return restClient;
    }

    public RestHighLevelClient getHighLevelClient(){
        if(restHighLevelClient == null){
        	init(config);
        }
        return restHighLevelClient;
    }

    public void close() {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                uniqueConnectNumConfig = false;
                uniqueConnectTimeConfig = false;
            }
        }
    }
}