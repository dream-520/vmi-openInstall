package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户在线记录表[t_user_online_log] 表对应的实体类
 * @author chengang
 * @Date 2017-05-10 18:20:18
 *
 */
@Table(name="t_user_online_log")
public class UserOnlineLogEntity extends BaseEntity implements Serializable {

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
	 * 登录时间
	 */
	@Column(name="login_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="登录时间")
	private Date login_time;
	
	/**
	 * 最后活动时间
	 */
	@Column(name="refresh_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后活动时间")
	private Date refresh_time;
	
	/**
	 * 客户端ID
	 */
	@Column(name="clientId",nullable=false,jdbcType=JdbcType.VARCHAR,comment="客户端ID")
	private String clientId;
	
	/**
	 * 在线时长(秒)
	 */
	@Column(name="times",nullable=false,jdbcType=JdbcType.INTEGER,comment="在线时长(秒)")
	private Integer times;
	
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
	
	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	
	public Date getRefresh_time() {
		return refresh_time;
	}

	public void setRefresh_time(Date refresh_time) {
		this.refresh_time = refresh_time;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
}