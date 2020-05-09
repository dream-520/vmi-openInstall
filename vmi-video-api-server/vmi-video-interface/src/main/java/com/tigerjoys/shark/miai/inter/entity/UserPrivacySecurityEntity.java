package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户隐私安全表[t_user_privacy_security] 表对应的实体类
 * @author lipeng
 * @Date 2017-12-25 10:26:14
 *
 */
@Table(name="t_user_privacy_security")
public class UserPrivacySecurityEntity extends BaseEntity implements Serializable {

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
	 * 密码
	 */
	@Column(name="password",nullable=true,jdbcType=JdbcType.CHAR,comment="密码")
	private String password;
	
	/**
	 * 开关状态 0.关 1.开
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="开关状态 0.关 1.开")
	private Integer status;
	
	/**
	 * 达人秀开关状态 0.关 1.开
	 */
	@Column(name="talent_status",nullable=false,jdbcType=JdbcType.TINYINT,comment="达人秀开关状态 0.关 1.开")
	private Integer talent_status;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getTalent_status() {
		return talent_status;
	}

	public void setTalent_status(Integer talent_status) {
		this.talent_status = talent_status;
	}
	
}