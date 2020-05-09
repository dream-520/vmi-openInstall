package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户注册信息记录表[t_user_reg_log] 表对应的实体类
 * @author chengang
 * @Date 2018-05-02 18:00:08
 *
 */
@Table(name="t_user_reg_log")
public class UserRegLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@Id(increment=false)
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
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
	 * 渠道名称
	 */
	@Column(name="channel",nullable=false,jdbcType=JdbcType.VARCHAR,comment="渠道名称")
	private String channel;
	
	/**
	 * 注册方式
	 */
	@Column(name="fr",nullable=false,jdbcType=JdbcType.TINYINT,comment="注册方式")
	private Integer fr;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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
	
	public Integer getFr() {
		return fr;
	}

	public void setFr(Integer fr) {
		this.fr = fr;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	@Override
	public Long getId() {
		return userid;
	}
}