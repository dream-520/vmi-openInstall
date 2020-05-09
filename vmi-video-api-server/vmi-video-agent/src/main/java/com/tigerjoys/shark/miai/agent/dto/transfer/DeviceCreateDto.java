package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;

/**
 * 创建设备的时候传递的Dto
 * @author chengang
 *
 */
public class DeviceCreateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8478658227413256661L;

	/**
	 * 当前登录的用户
	 */
	private Long userid;
	
	/**
	 * 当前设备对应的android
	 */
	private String androidId;
	
	/**
	 * 设备ID
	 */
	private String clientid;
	
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
	 * imei
	 */
	private String imei;
	
	/**
	 * imsi
	 */
	private String imsi;
	
	/**
	 * mac
	 */
	private String mac;
	
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
	 * pkgmd5
	 */
	private String pkgmd5;
	
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
	 * sdk版本
	 */
	private String sdk_ver;
	
	/**
	 * 系统版本
	 */
	private String os_ver;
	
	/**
	 * 系统版本
	 */
	private Integer platform;
	
	/**
	 * 渠道名称
	 */
	private String channel;
	
	/**
	 * 包名
	 */
	private String pkg_name;
	
	/**
	 * IP地址
	 */
	private String ip;
	
	/**
	 * sdk卡的总容量,GB
	 */
	private Integer sdk_volume;
	
	/**
	 * sdk卡的剩余容量,GB
	 */
	private Integer sdk_remain;
	
	/**
	 * rom的总容量,GB
	 */
	private Integer rom_volume;
	
	/**
	 * rom的剩余容量,GB
	 */
	private Integer rom_remain;
	
	/**
	 * 通知栏是否被关闭 1开启,2关闭,0或null无值
	 */
	private Integer noticeClose;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

	public String getPkgmd5() {
		return pkgmd5;
	}

	public void setPkgmd5(String pkgmd5) {
		this.pkgmd5 = pkgmd5;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getSdk_volume() {
		return sdk_volume;
	}

	public void setSdk_volume(Integer sdk_volume) {
		this.sdk_volume = sdk_volume;
	}

	public Integer getSdk_remain() {
		return sdk_remain;
	}

	public void setSdk_remain(Integer sdk_remain) {
		this.sdk_remain = sdk_remain;
	}

	public Integer getRom_volume() {
		return rom_volume;
	}

	public void setRom_volume(Integer rom_volume) {
		this.rom_volume = rom_volume;
	}

	public Integer getRom_remain() {
		return rom_remain;
	}

	public void setRom_remain(Integer rom_remain) {
		this.rom_remain = rom_remain;
	}

	public String getPkg_name() {
		return pkg_name;
	}

	public void setPkg_name(String pkg_name) {
		this.pkg_name = pkg_name;
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

}
