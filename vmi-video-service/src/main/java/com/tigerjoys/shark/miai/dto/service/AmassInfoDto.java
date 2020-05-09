package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 信息收集Dto
 * @author chengang
 *
 */
public class AmassInfoDto {
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 城市ID
	 */
	private long city;
	
	/**
	 * 定位的经度
	 */
	private double lng;
	
	/**
	 * 定位的纬度
	 */
	private double lat;
	
	/**
	 * imei
	 */
	private String imei1;
	
	/**
	 * imsi
	 */
	private String imsi1;
	
	/**
	 * imei
	 */
	private String imei2;
	
	/**
	 * imsi
	 */
	private String imsi2;
	
	/**
	 * mac地址
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
	 * cpu核数
	 */
	private int core;
	
	/**
	 * cpu型号
	 */
	private String coreModel;
	
	/**
	 * pkgMd5
	 */
	private String pkgMd5;
	
	/**
	 * 屏幕分辨率
	 */
	private String screenSize;
	
	/**
	 * 手机型号
	 */
	private String mobileModel;
	
	/**
	 * 手机品牌
	 */
	private String mobileBrand;
	
	/**
	 * SDK版本
	 */
	private String sdkVersion;
	
	/**
	 * 系统版本
	 */
	private String releaseVersion;
	
	/**
	 * 包名
	 */
	private String pkgName;
	
	/**
	 * sdk卡的总容量,GB
	 */
	private Integer sdkVolume;
	
	/**
	 * sdk卡的剩余容量,GB
	 */
	private Integer sdkRemain;
	
	/**
	 * rom的总容量,GB
	 */
	private Integer romVolume;
	
	/**
	 * rom的剩余容量,GB
	 */
	private Integer romRemain;
	
	/**
	 * 通知栏是否被关闭 1开启,2关闭,0或null无值
	 */
	private Integer noticeClose;
	
	/**
	 * 用户的应用列表
	 */
	private List<String> appList;
	
	/**
	 * 用户当前打开的应用
	 */
	private List<String> curAppList;
	
	private String clientId;
	
	private String androidId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getCity() {
		return city;
	}

	public void setCity(long city) {
		this.city = city;
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

	public String getImei1() {
		return imei1;
	}

	public void setImei1(String imei1) {
		this.imei1 = imei1;
	}

	public String getImsi1() {
		return imsi1;
	}

	public void setImsi1(String imsi1) {
		this.imsi1 = imsi1;
	}

	public String getImei2() {
		return imei2;
	}

	public void setImei2(String imei2) {
		this.imei2 = imei2;
	}

	public String getImsi2() {
		return imsi2;
	}

	public void setImsi2(String imsi2) {
		this.imsi2 = imsi2;
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

	public int getCore() {
		return core;
	}

	public void setCore(int core) {
		this.core = core;
	}

	public String getPkgMd5() {
		return pkgMd5;
	}

	public void setPkgMd5(String pkgMd5) {
		this.pkgMd5 = pkgMd5;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	public String getMobileBrand() {
		return mobileBrand;
	}

	public void setMobileBrand(String mobileBrand) {
		this.mobileBrand = mobileBrand;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public List<String> getAppList() {
		return appList;
	}

	public void setAppList(List<String> appList) {
		this.appList = appList;
	}

	public List<String> getCurAppList() {
		return curAppList;
	}

	public void setCurAppList(List<String> curAppList) {
		this.curAppList = curAppList;
	}

	public Integer getSdkVolume() {
		return sdkVolume;
	}

	public void setSdkVolume(Integer sdkVolume) {
		this.sdkVolume = sdkVolume;
	}

	public Integer getSdkRemain() {
		return sdkRemain;
	}

	public void setSdkRemain(Integer sdkRemain) {
		this.sdkRemain = sdkRemain;
	}

	public Integer getRomVolume() {
		return romVolume;
	}

	public void setRomVolume(Integer romVolume) {
		this.romVolume = romVolume;
	}

	public Integer getRomRemain() {
		return romRemain;
	}

	public void setRomRemain(Integer romRemain) {
		this.romRemain = romRemain;
	}

	public String getCoreModel() {
		return coreModel;
	}

	public void setCoreModel(String coreModel) {
		this.coreModel = coreModel;
	}

	public Integer getNoticeClose() {
		return noticeClose;
	}

	public void setNoticeClose(Integer noticeClose) {
		this.noticeClose = noticeClose;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmassInfoDto [mobile=");
		builder.append(mobile);
		builder.append(", city=");
		builder.append(city);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", imei1=");
		builder.append(imei1);
		builder.append(", imsi1=");
		builder.append(imsi1);
		builder.append(", imei2=");
		builder.append(imei2);
		builder.append(", imsi2=");
		builder.append(imsi2);
		builder.append(", mac=");
		builder.append(mac);
		builder.append(", bssid=");
		builder.append(bssid);
		builder.append(", ssid=");
		builder.append(ssid);
		builder.append(", core=");
		builder.append(core);
		builder.append(", coreModel=");
		builder.append(coreModel);
		builder.append(", pkgMd5=");
		builder.append(pkgMd5);
		builder.append(", screenSize=");
		builder.append(screenSize);
		builder.append(", mobileModel=");
		builder.append(mobileModel);
		builder.append(", mobileBrand=");
		builder.append(mobileBrand);
		builder.append(", sdkVersion=");
		builder.append(sdkVersion);
		builder.append(", releaseVersion=");
		builder.append(releaseVersion);
		builder.append(", pkgName=");
		builder.append(pkgName);
		builder.append(", sdkVolume=");
		builder.append(sdkVolume);
		builder.append(", sdkRemain=");
		builder.append(sdkRemain);
		builder.append(", romVolume=");
		builder.append(romVolume);
		builder.append(", romRemain=");
		builder.append(romRemain);
		builder.append(", noticeClose=");
		builder.append(noticeClose);
		builder.append(", appList=");
		builder.append(appList);
		builder.append(", curAppList=");
		builder.append(curAppList);
		builder.append("]");
		return builder.toString();
	}

}
