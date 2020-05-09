package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  ios设备udid[t_app_ios_device_udid] 表对应的实体类
 * @author yangjunming
 * @Date 2019-07-23 10:19:14
 *
 */
@Table(name="t_app_ios_device_udid")
public class AppIosDeviceUdidEntity extends BaseEntity implements Serializable {

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
	 * udid
	 */
	@Column(name="udid",nullable=false,jdbcType=JdbcType.VARCHAR,comment="udid")
	private String udid;
	
	/**
	 * 型号
	 */
	@Column(name="product",nullable=true,jdbcType=JdbcType.VARCHAR,comment="型号")
	private String product;
	
	/**
	 * 版本
	 */
	@Column(name="version",nullable=true,jdbcType=JdbcType.VARCHAR,comment="版本")
	private String version;
	
	/**
	 * imei
	 */
	@Column(name="imei",nullable=true,jdbcType=JdbcType.VARCHAR,comment="imei")
	private String imei;
	
	/**
	 * 证书
	 */
	@Column(name="certificate",nullable=true,jdbcType=JdbcType.VARCHAR,comment="证书")
	private String certificate;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}