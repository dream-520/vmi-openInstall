package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户登录请求的Dto
 * @author lipeng
 *
 */
public class UserLoginBO implements Serializable {

	private static final long serialVersionUID = 6721481548201785078L;
	
	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 终端ID
	 */
	private String client_id;
	
	/**
	 * 当前经度
	 */
	private Double lng;
	
	/**
	 * 当前纬度
	 */
	private Double lat;
	
	/**
	 * 当前IP
	 */
	private String ip;
	
	/**
	 * 定位到的城市
	 */
	private String city_name;
	
	/**
	 * app类型,0直播端,1主播端
	 */
	private Integer apptype;
	
	/**
	 * app版本
	 */
	private String appversion;
	
	/**
	 * 渠道
	 */
	private String channel;
	
	/**
	 * 渠道
	 */
	private String package_name;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Integer getApptype() {
		return apptype;
	}

	public void setApptype(Integer apptype) {
		this.apptype = apptype;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
}
