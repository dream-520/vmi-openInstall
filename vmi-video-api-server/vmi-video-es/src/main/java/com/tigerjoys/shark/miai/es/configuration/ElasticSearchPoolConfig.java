package com.tigerjoys.shark.miai.es.configuration;

public class ElasticSearchPoolConfig {
	
	/**
	 * IP地址，可以多个
	 */
	private String host;
	
	/**
	 * 端口号
	 */
	private int port ;
	
	/**
	 * 用户名
	 */
	private String user;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 协义
	 */
	private String schema;
	
	/**
	 * 连接超时时间
	 */
	private int connectTimeOut  ;
	
	/**
	 * socket超时时间
	 */
	private int socketTimeOut  ;
	
	/**
	 * 请求超时时间
	 */
	private int connectionRequestTimeOut  ;
	
	/**
	 * 最大连接数
	 */
	private int maxConnectNum  ;
	
	/**
	 * 预备最大连接数
	 */
	private int maxConnectPerRoute  ;

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
