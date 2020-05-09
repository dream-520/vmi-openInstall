package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  升级弹窗log表[t_home_pop_version_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-03 13:21:47
 *
 */
@Table(name="t_home_pop_version_log")
public class HomePopVersionLogEntity extends BaseEntity implements Serializable {

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
	@Column(name="client_id",nullable=false,jdbcType=JdbcType.VARCHAR,comment="设备ID")
	private String client_id;
	
	/**
	 * 版本code
	 */
	@Column(name="version_code",nullable=false,jdbcType=JdbcType.INTEGER,comment="版本code")
	private Integer version_code;
	
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
	
	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
	public Integer getVersion_code() {
		return version_code;
	}

	public void setVersion_code(Integer version_code) {
		this.version_code = version_code;
	}
	
}