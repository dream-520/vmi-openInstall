package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  快手下发的广告数据[t_kuai_shou_ad_data] 表对应的实体类
 * @author shiming
 * @Date 2019-10-06 17:35:46
 *
 */
@Table(name="t_kuai_shou_ad_data")
public class KuaiShouAdDataEntity extends BaseEntity implements Serializable {

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
	 * android对应的标识id
	 */
	@Column(name="androidId",nullable=true,jdbcType=JdbcType.VARCHAR,comment="android对应的标识id")
	private String androidId;
	
	/**
	 * 手机的mac地址
	 */
	@Column(name="mac",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机的mac地址")
	private String mac;
	
	/**
	 * 手机对应的ip地址
	 */
	@Column(name="ip",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机对应的ip地址")
	private String ip;
	
	/**
	 * 手机的imei
	 */
	@Column(name="imei",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机的imei")
	private String imei;
	
	/**
	 * 对应的回调地址
	 */
	@Column(name="callback",nullable=true,jdbcType=JdbcType.VARCHAR,comment="对应的回调地址")
	private String callback;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 状态
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="审查机制 0待检测 1 以传入快手 -1 传入快手失败")
	private Integer state;
	
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
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}