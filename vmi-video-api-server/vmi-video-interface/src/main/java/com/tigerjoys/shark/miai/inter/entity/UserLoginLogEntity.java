package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户登录信息变换记录表[t_user_login_log] 表对应的实体类
 * @author lipeng
 * @Date 2017-06-08 11:57:22
 *
 */
@Table(name="t_user_login_log")
public class UserLoginLogEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 设备ID
	 */
	@Column(name="client_id",nullable=false,jdbcType=JdbcType.CHAR,comment="设备ID")
	private String client_id;
	
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
	 * ip地址
	 */
	@Column(name="ip",nullable=true,jdbcType=JdbcType.VARCHAR,comment="ip地址")
	private String ip;
	
	/**
	 * 定位城市名称
	 */
	@Column(name="city_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="定位城市名称")
	private String city_name;
	
	/**
	 * 类型--1，登录状态；2，定位改变；3，退出登录；4，注册
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型--1，登录状态；2，定位改变；3，退出登录；4，注册")
	private Integer type;
	
	/**
	 * 登录APP类型
	 */
	@Column(name="apptype",nullable=true,jdbcType=JdbcType.TINYINT,comment="登录APP类型")
	private Integer apptype;
	
	/**
	 * 登录APP版本
	 */
	@Column(name="appversion",nullable=true,jdbcType=JdbcType.VARCHAR,comment="登录APP版本")
	private String appversion;
	
	/**
	 * 登录渠道
	 */
	@Column(name="channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="登录渠道")
	private String channel;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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