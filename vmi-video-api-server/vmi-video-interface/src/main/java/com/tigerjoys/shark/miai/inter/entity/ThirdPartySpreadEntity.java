package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  三方广告推荐注册的设备和用户信息[t_third_party_spread] 表对应的实体类
 * @author shiming
 * @Date 2020-01-03 16:49:03
 *
 */
@Table(name="t_third_party_spread")
public class ThirdPartySpreadEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标识
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标识")
	private Long id;
	
	/**
	 * android设备标识
	 */
	@Column(name="androidId",nullable=true,jdbcType=JdbcType.VARCHAR,comment="android设备标识")
	private String androidId;
	
	/**
	 * 设备标识
	 */
	@Column(name="clientId",nullable=true,jdbcType=JdbcType.VARCHAR,comment="设备标识")
	private String clientId;
	
	/**
	 * 用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 来源 1快手 
	 */
	@Column(name="origin",nullable=true,jdbcType=JdbcType.TINYINT,comment="来源 1快手 ")
	private Integer origin;
	
	/**
	 * 初始注册渠道
	 */
	@Column(name="reg_channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="初始注册渠道")
	private String reg_channel;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}
	
	public String getReg_channel() {
		return reg_channel;
	}

	public void setReg_channel(String reg_channel) {
		this.reg_channel = reg_channel;
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
	
}