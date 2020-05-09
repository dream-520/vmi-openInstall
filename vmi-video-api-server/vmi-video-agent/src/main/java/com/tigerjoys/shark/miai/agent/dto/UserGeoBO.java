package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户位置信息DTO
 * @author chengang
 *
 */
public class UserGeoBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1145601221305314546L;
	
	/**
	 * ID
	 */
	private long id;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 经度
	 */
	private double lng;
	
	/**
	 * 纬度
	 */
	private double lat;
	
	/**
	 * GEO HASH 值
	 */
	private String geoCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

}
