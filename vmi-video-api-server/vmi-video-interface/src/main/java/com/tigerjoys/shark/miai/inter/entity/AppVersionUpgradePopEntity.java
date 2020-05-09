package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  版本升级弹窗表[t_app_version_upgrade_pop] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-03 13:21:47
 *
 */
@Table(name="t_app_version_upgrade_pop")
public class AppVersionUpgradePopEntity extends BaseEntity implements Serializable {

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
	 * 版本
	 */
	@Column(name="version_text",nullable=false,jdbcType=JdbcType.VARCHAR,comment="版本")
	private String version_text;
	
	/**
	 * 版本code
	 */
	@Column(name="version_code",nullable=false,jdbcType=JdbcType.INTEGER,comment="版本code")
	private Integer version_code;
	
	/**
	 * 弹窗地址
	 */
	@Column(name="version_url",nullable=false,jdbcType=JdbcType.VARCHAR,comment="弹窗地址")
	private String version_url;
	
	/**
	 * 状态 0 删除 1 正常
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态 0 删除 1 正常")
	private Integer status;
	
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
	
	public String getVersion_text() {
		return version_text;
	}

	public void setVersion_text(String version_text) {
		this.version_text = version_text;
	}
	
	public Integer getVersion_code() {
		return version_code;
	}

	public void setVersion_code(Integer version_code) {
		this.version_code = version_code;
	}
	
	public String getVersion_url() {
		return version_url;
	}

	public void setVersion_url(String version_url) {
		this.version_url = version_url;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}