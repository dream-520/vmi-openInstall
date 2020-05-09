package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  设备表[t_device] 表对应的实体类
 * @author lipeng
 * @Date 2019-08-08 14:51:51
 *
 */
@Table(name="t_device")
public class DeviceEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 设备ID
	 */
	@Column(name="clientid",nullable=false,jdbcType=JdbcType.CHAR,comment="设备ID")
	private String clientid;
	
	/**
	 * 手机号码
	 */
	@Column(name="mobile",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机号码")
	private String mobile;
	
	/**
	 * 所在国家ID
	 */
	@Column(name="country_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="所在国家ID")
	private Long country_id;
	
	/**
	 * 所在省份ID
	 */
	@Column(name="province_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="所在省份ID")
	private Long province_id;
	
	/**
	 * 定位城市ID
	 */
	@Column(name="city_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="定位城市ID")
	private Long city_id;
	
	/**
	 * 定位经度
	 */
	@Column(name="lng",nullable=false,jdbcType=JdbcType.DOUBLE,comment="定位经度")
	private Double lng;
	
	/**
	 * 定位纬度
	 */
	@Column(name="lat",nullable=false,jdbcType=JdbcType.DOUBLE,comment="定位纬度")
	private Double lat;
	
	/**
	 * imei
	 */
	@Column(name="imei",nullable=true,jdbcType=JdbcType.VARCHAR,comment="imei")
	private String imei;
	
	/**
	 * imsi
	 */
	@Column(name="imsi",nullable=true,jdbcType=JdbcType.VARCHAR,comment="imsi")
	private String imsi;
	
	/**
	 * mac
	 */
	@Column(name="mac",nullable=true,jdbcType=JdbcType.VARCHAR,comment="mac")
	private String mac;
	
	/**
	 * bssid
	 */
	@Column(name="bssid",nullable=true,jdbcType=JdbcType.VARCHAR,comment="bssid")
	private String bssid;
	
	/**
	 * ssid
	 */
	@Column(name="ssid",nullable=true,jdbcType=JdbcType.VARCHAR,comment="ssid")
	private String ssid;
	
	/**
	 * CPU核数
	 */
	@Column(name="core",nullable=true,jdbcType=JdbcType.SMALLINT,comment="CPU核数")
	private Integer core;
	
	/**
	 * CPU型号
	 */
	@Column(name="core_model",nullable=true,jdbcType=JdbcType.VARCHAR,comment="CPU型号")
	private String core_model;
	
	/**
	 * pkgmd5
	 */
	@Column(name="pkgmd5",nullable=true,jdbcType=JdbcType.VARCHAR,comment="pkgmd5")
	private String pkgmd5;
	
	/**
	 * 应用的版本号
	 */
	@Column(name="appversion",nullable=true,jdbcType=JdbcType.VARCHAR,comment="应用的版本号")
	private String appversion;
	
	/**
	 * 应用的序列号
	 */
	@Column(name="versioncode",nullable=false,jdbcType=JdbcType.INTEGER,comment="应用的序列号")
	private Integer versioncode;
	
	/**
	 * 屏幕分辨率
	 */
	@Column(name="screensize",nullable=true,jdbcType=JdbcType.VARCHAR,comment="屏幕分辨率")
	private String screensize;
	
	/**
	 * 手机型号
	 */
	@Column(name="mobile_model",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机型号")
	private String mobile_model;
	
	/**
	 * 手机品牌
	 */
	@Column(name="mobile_brand",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机品牌")
	private String mobile_brand;
	
	/**
	 * sdk版本
	 */
	@Column(name="sdk_ver",nullable=true,jdbcType=JdbcType.VARCHAR,comment="sdk版本")
	private String sdk_ver;
	
	/**
	 * 系统版本
	 */
	@Column(name="os_ver",nullable=true,jdbcType=JdbcType.VARCHAR,comment="系统版本")
	private String os_ver;
	
	/**
	 * 平台,0未知,1And,2IOS
	 */
	@Column(name="platform",nullable=false,jdbcType=JdbcType.TINYINT,comment="平台,0未知,1And,2IOS")
	private Integer platform;
	
	/**
	 * 渠道名称
	 */
	@Column(name="channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="渠道名称")
	private String channel;
	
	/**
	 * 包名
	 */
	@Column(name="pkg_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String pkg_name;
	
	/**
	 * IP地址
	 */
	@Column(name="ip",nullable=true,jdbcType=JdbcType.VARCHAR,comment="IP地址")
	private String ip;
	
	/**
	 * 1正常,0查封
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常,0查封")
	private Integer status;
	
	/**
	 * 注册城市ID
	 */
	@Column(name="reg_city_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="注册城市ID")
	private Long reg_city_id;
	
	/**
	 * 注册时的渠道名称
	 */
	@Column(name="reg_channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="注册时的渠道名称")
	private String reg_channel;
	
	/**
	 * 注册时候的版本
	 */
	@Column(name="reg_appversion",nullable=true,jdbcType=JdbcType.VARCHAR,comment="注册时候的版本")
	private String reg_appversion;
	
	/**
	 * 注册序列号
	 */
	@Column(name="reg_versioncode",nullable=false,jdbcType=JdbcType.INTEGER,comment="注册序列号")
	private Integer reg_versioncode;
	
	/**
	 * sdk卡的总容量,GB
	 */
	@Column(name="sdk_volume",nullable=true,jdbcType=JdbcType.SMALLINT,comment="sdk卡的总容量,GB")
	private Integer sdk_volume;
	
	/**
	 * sdk卡的剩余容量,GB
	 */
	@Column(name="sdk_remain",nullable=true,jdbcType=JdbcType.SMALLINT,comment="sdk卡的剩余容量,GB")
	private Integer sdk_remain;
	
	/**
	 * rom的总容量,GB
	 */
	@Column(name="rom_volume",nullable=true,jdbcType=JdbcType.SMALLINT,comment="rom的总容量,GB")
	private Integer rom_volume;
	
	/**
	 * rom的剩余容量,GB
	 */
	@Column(name="rom_remain",nullable=true,jdbcType=JdbcType.SMALLINT,comment="rom的剩余容量,GB")
	private Integer rom_remain;
	
	/**
	 * 通知栏是否被关闭,1开启,2关闭,0或者null未知
	 */
	@Column(name="notice_close",nullable=true,jdbcType=JdbcType.TINYINT,comment="通知栏是否被关闭,1开启,2关闭,0或者null未知")
	private Integer notice_close;
	
	/**
	 * 设置对应的androidId值
	 */
	@Column(name="androidId",nullable=true,jdbcType=JdbcType.VARCHAR,comment="设置对应的androidId值")
	private String androidId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
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
	
	public String getCore_model() {
		return core_model;
	}

	public void setCore_model(String core_model) {
		this.core_model = core_model;
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
	
	public String getPkg_name() {
		return pkg_name;
	}

	public void setPkg_name(String pkg_name) {
		this.pkg_name = pkg_name;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
	
	public Integer getNotice_close() {
		return notice_close;
	}

	public void setNotice_close(Integer notice_close) {
		this.notice_close = notice_close;
	}
	
	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	
}