package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import org.apache.ibatis.type.JdbcType;

import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户定位信息表[t_user_geo] 表对应的实体类
 * @author chengang
 * @Date 2017-06-02 12:25:24
 *
 */
@Table(name="t_user_geo")
public class UserGeoEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 经度
	 */
	@Column(name="lng",nullable=false,jdbcType=JdbcType.DOUBLE,comment="经度")
	private Double lng;
	
	/**
	 * 纬度
	 */
	@Column(name="lat",nullable=false,jdbcType=JdbcType.DOUBLE,comment="纬度")
	private Double lat;
	
	/**
	 * 海拔高度,米
	 */
	@Column(name="alt",nullable=false,jdbcType=JdbcType.DOUBLE,comment="海拔高度,米")
	private Double alt;
	
	/**
	 * geo编码,9位编码
	 */
	@Column(name="geo_code",nullable=false,jdbcType=JdbcType.CHAR,comment="geo编码")
	private String geo_code;
	
	/**
	 * 方向,度
	 */
	@Column(name="direction",nullable=false,jdbcType=JdbcType.DOUBLE,comment="方向,度")
	private Double direction;
	
	/**
	 * 速度,公里/小时
	 */
	@Column(name="speed",nullable=false,jdbcType=JdbcType.DOUBLE,comment="速度,公里/小时")
	private Double speed;
	
	/**
	 * 城市名称
	 */
	@Column(name="city",nullable=true,jdbcType=JdbcType.VARCHAR,comment="城市名称")
	private String city;
	
	/**
	 * 城市Code
	 */
	@Column(name="city_code",nullable=true,jdbcType=JdbcType.INTEGER,comment="城市Code")
	private Integer city_code;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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
	
	public Double getAlt() {
		return alt;
	}

	public void setAlt(Double alt) {
		this.alt = alt;
	}
	
	public Double getDirection() {
		return direction;
	}

	public void setDirection(Double direction) {
		this.direction = direction;
	}
	
	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getGeo_code() {
		return geo_code;
	}

	public void setGeo_code(String geo_code) {
		this.geo_code = geo_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCity_code() {
		return city_code;
	}

	public void setCity_code(Integer city_code) {
		this.city_code = city_code;
	}
	
}