package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备信息DTO
 * @author chengang
 *
 */
public class DeviceBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -129390762841508495L;
	
	/**
	 * 设备ID
	 */
	private Long deviceId;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 设备ID
	 */
	private String clientid;
	
	/**
	 * 所在国家ID
	 */
	private Long country_id;
	
	/**
	 * 所在省份ID
	 */
	private Long province_id;
	/**
	 * 定位城市ID
	 */
	private Long city_id;
	
	/**
	 * 定位经度
	 */
	private Double lng;
	
	/**
	 * 定位纬度
	 */
	private Double lat;
	
	/**
	 * imei
	 */
	private String imei;
	
	/**
	 * mac
	 */
	private String mac;
	
	/**
	 * 应用的版本号
	 */
	private String appversion;
	
	/**
	 * 应用的序列号
	 */
	private Integer versioncode;
	
	/**
	 * 屏幕分辨率
	 */
	private String screensize;
	
	/**
	 * 手机型号
	 */
	private String mobile_model;
	
	/**
	 * 手机品牌
	 */
	private String mobile_brand;
	
	/**
	 * 系统平台
	 */
	private Integer platform;
	
	/**
	 * 渠道名称
	 */
	private String channel;
	
	/**
	 * 1正常,0查封
	 */
	private Integer status;
	
	/**
	 * 注册城市ID
	 */
	private Long reg_city_id;
	
	/**
	 * 注册时的渠道名称
	 */
	private String reg_channel;
	
	/**
	 * 注册时候的版本
	 */
	private String reg_appversion;
	
	/**
	 * 注册时候的序列号
	 */
	private Integer reg_versioncode;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public Long getCountry_id() {
		return country_id;
	}

	public void setCountry_id(Long country_id) {
		this.country_id = country_id;
	}

	public Long getProvince_id() {
		return province_id;
	}

	public void setProvince_id(Long province_id) {
		this.province_id = province_id;
	}

	public Long getCity_id() {
		return city_id;
	}

	public void setCity_id(Long city_id) {
		this.city_id = city_id;
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public Integer getVersioncode() {
		return versioncode;
	}

	public void setVersioncode(Integer versioncode) {
		this.versioncode = versioncode;
	}

	public String getScreensize() {
		return screensize;
	}

	public void setScreensize(String screensize) {
		this.screensize = screensize;
	}

	public String getMobile_model() {
		return mobile_model;
	}

	public void setMobile_model(String mobile_model) {
		this.mobile_model = mobile_model;
	}

	public String getMobile_brand() {
		return mobile_brand;
	}

	public void setMobile_brand(String mobile_brand) {
		this.mobile_brand = mobile_brand;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getReg_city_id() {
		return reg_city_id;
	}

	public void setReg_city_id(Long reg_city_id) {
		this.reg_city_id = reg_city_id;
	}

	public String getReg_channel() {
		return reg_channel;
	}

	public void setReg_channel(String reg_channel) {
		this.reg_channel = reg_channel;
	}

	public String getReg_appversion() {
		return reg_appversion;
	}

	public void setReg_appversion(String reg_appversion) {
		this.reg_appversion = reg_appversion;
	}

	public Integer getReg_versioncode() {
		return reg_versioncode;
	}

	public void setReg_versioncode(Integer reg_versioncode) {
		this.reg_versioncode = reg_versioncode;
	}

}
