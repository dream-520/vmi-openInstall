package com.tigerjoys.shark.miai.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.search.elastic")
public class ElasticsearchProperties {
	
	private String host;
	
	private int port;
	
	private String user;
	
	private String password;
	
	private String schema;
	
	private int connectTimeOut;
	
	private int socketTimeOut;
	
	private int connectionRequestTimeOut;
	
	private int maxConnectNum;
	
	private int maxConnectPerRoute;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	public int getSocketTimeOut() {
		return socketTimeOut;
	}

	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}

	public int getConnectionRequestTimeOut() {
		return connectionRequestTimeOut;
	}

	public void setConnectionRequestTimeOut(int connectionRequestTimeOut) {
		this.connectionRequestTimeOut = connectionRequestTimeOut;
	}

	public int getMaxConnectNum() {
		return maxConnectNum;
	}

	public void setMaxConnectNum(int maxConnectNum) {
		this.maxConnectNum = maxConnectNum;
	}

	public int getMaxConnectPerRoute() {
		return maxConnectPerRoute;
	}

	public void setMaxConnectPerRoute(int maxConnectPerRoute) {
		this.maxConnectPerRoute = maxConnectPerRoute;
	}

}
