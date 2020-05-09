package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户诚信徽章表[t_user_honesty_badge] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-11-14 10:29:00
 *
 */
@Table(name="t_user_honesty_badge")
public class UserHonestyBadgeEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@Id(increment=false)
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
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
	 * 一言为定到期时间
	 */
	@Column(name="low_expire_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="一言为定到期时间")
	private Date low_expire_time;
	
	/**
	 * 一诺千金到期时间
	 */
	@Column(name="mid_expire_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="一诺千金到期时间")
	private Date mid_expire_time;
	
	/**
	 * 一言九鼎到期时间
	 */
	@Column(name="high_expire_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="一言九鼎到期时间")
	private Date high_expire_time;
	
	/**
	 * 0-否，1-是；是否在有效期
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="0-否，1-是；是否在有效期")
	private Integer status;
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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
	
	public Date getLow_expire_time() {
		return low_expire_time;
	}

	public void setLow_expire_time(Date low_expire_time) {
		this.low_expire_time = low_expire_time;
	}
	
	public Date getMid_expire_time() {
		return mid_expire_time;
	}

	public void setMid_expire_time(Date mid_expire_time) {
		this.mid_expire_time = mid_expire_time;
	}
	
	public Date getHigh_expire_time() {
		return high_expire_time;
	}

	public void setHigh_expire_time(Date high_expire_time) {
		this.high_expire_time = high_expire_time;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public Long getId() {
		return user_id;
	}
}