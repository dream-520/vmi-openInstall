package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import org.apache.ibatis.type.JdbcType;

import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户svip信息[t_user_svip] 表对应的实体类
 * @author chengang
 * @Date 2018-01-26 08:21:25
 *
 */
@Table(name="t_user_svip")
public class UserSvipEntity extends BaseEntity implements Serializable {

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
	 * 更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * SVIP等级
	 */
	@Column(name="level",nullable=false,jdbcType=JdbcType.TINYINT,comment="SVIP等级")
	private Integer level;
	
	/**
	 * 升级时间
	 */
	@Column(name="upgrade_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="升级时间")
	private Date upgrade_time;
	
	/**
	 * 过期时间
	 */
	@Column(name="expire_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="过期时间")
	private Date expire_time;
	
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Date getUpgrade_time() {
		return upgrade_time;
	}

	public void setUpgrade_time(Date upgrade_time) {
		this.upgrade_time = upgrade_time;
	}
	
	public Date getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}
	
	@Override
	public Long getId() {
		return userid;
	}
}