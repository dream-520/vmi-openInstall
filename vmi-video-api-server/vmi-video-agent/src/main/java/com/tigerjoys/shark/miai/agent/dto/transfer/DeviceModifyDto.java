package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;

/**
 * 修改设备基本信息Dto
 * @author chengang
 *
 */
public class DeviceModifyDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -914354337375560243L;
	
	/**
	 * 设备ID
	 */
	private long deviceId;
	
	/**
	 * 当前设备对应的android
	 */
	private String androidId;
	
	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
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
	 * bssid
	 */
	private String bssid;
	
	/**
	 * ssid
	 */
	private String ssid;
	
	/**
	 * CPU核数
	 */
	private Integer core;
	
	/**
	 * CPU型号
	 */
	private String core_model;
	
	/**
	 * sdk版本
	 */
	private String sdk_ver;
	
	/**
	 * 系统版本
	 */
	private String os_ver;
	
	/**
	 * 应用的版本号
	 */
	private String appversion;
	
	/**
	 * 应用的序列号
	 */
	private Integer versioncode;
	
	/**
	 * 渠道名称
	 */
	private String channel;
	
	/**
	 * IP地址
	 */
	private String ip;
	
	/**
	 * 通知栏是否被关闭 1开启,2关闭,0或null无值
	 */
	private Integer noticeClose;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public Integer getCore() {
		return core;
	}

	public void setCore(Integer core) {
		this.core = core;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getSdk_ver() {
		return sdk_ver;
	}

	public void setSdk_ver(String sdk_ver) {
		this.sdk_ver = sdk_ver;
	}

	public String getOs_ver() {
		return os_ver;
	}

	public void setOs_ver(String os_ver) {
		this.os_ver = os_ver;
	}

	public String getCore_model() {
		return core_model;
	}

	public void setCore_model(String core_model) {
		this.core_model = core_model;
	}

	public Integer getNoticeClose() {
		return noticeClose;
	}

	public void setNoticeClose(Integer noticeClose) {
		this.noticeClose = noticeClose;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	
}
