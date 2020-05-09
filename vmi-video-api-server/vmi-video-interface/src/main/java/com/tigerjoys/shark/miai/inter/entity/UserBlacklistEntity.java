package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户黑名单表[t_user_blacklist] 表对应的实体类
 * @author yangjunming
 * @Date 2019-06-27 11:35:20
 *
 */
@Table(name="t_user_blacklist")
public class UserBlacklistEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="主键ID")
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
	 * 黑名单ID
	 */
	@Column(name="blackid",nullable=false,jdbcType=JdbcType.BIGINT,comment="黑名单ID")
	private Long blackid;
	
	/**
	 * 投诉信息
	 */
	@Column(name="evaluation_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="投诉信息")
	private String evaluation_text;
	
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
	
	public Long getBlackid() {
		return blackid;
	}

	public void setBlackid(Long blackid) {
		this.blackid = blackid;
	}
	
	public String getEvaluation_text() {
		return evaluation_text;
	}

	public void setEvaluation_text(String evaluation_text) {
		this.evaluation_text = evaluation_text;
	}
	
}