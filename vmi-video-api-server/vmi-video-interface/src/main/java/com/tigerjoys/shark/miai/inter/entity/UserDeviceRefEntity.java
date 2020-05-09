package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  注册设备跟直播用户的关联表[t_user_device_ref] 表对应的实体类
 * @author chengang
 * @Date 2017-04-12 11:43:23
 *
 */
@Table(name="t_user_device_ref")
public class UserDeviceRefEntity extends BaseEntity implements Serializable {

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
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 设备ID
	 */
	@Column(name="deviceid",nullable=false,jdbcType=JdbcType.BIGINT,comment="设备ID")
	private Long deviceid;
	
	/**
	 * 1正常，0查封
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常，0查封")
	private Integer status;
	
	/**
	 * 版本号
	 */
	@Column(name="app_version",nullable=true,jdbcType=JdbcType.VARCHAR,comment="版本号")
	private String app_version;
	
	/**
	 * 版本序列号
	 */
	@Column(name="app_version_code",nullable=true,jdbcType=JdbcType.INTEGER,comment="版本序列号")
	private Integer app_version_code;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Long deviceid) {
		this.deviceid = deviceid;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	
	public Integer getApp_version_code() {
		return app_version_code;
	}

	public void setApp_version_code(Integer app_version_code) {
		this.app_version_code = app_version_code;
	}
	
}