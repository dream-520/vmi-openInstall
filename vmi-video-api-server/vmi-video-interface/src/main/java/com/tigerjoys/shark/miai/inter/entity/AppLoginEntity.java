package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  第三方登录表[t_app_login] 表对应的实体类
 * @author lipeng
 * @Date 2017-05-06 10:28:58
 *
 */
@Table(name="t_app_login")
public class AppLoginEntity extends BaseEntity implements Serializable {

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
	 * openid
	 */
	@Column(name="openid",nullable=false,jdbcType=JdbcType.VARCHAR,comment="openid")
	private String openid;
	
	/**
	 * 授权日期
	 */
	@Column(name="access_date",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="授权日期")
	private Date access_date;
	
	/**
	 * 授权token
	 */
	@Column(name="access_token",nullable=false,jdbcType=JdbcType.VARCHAR,comment="授权token")
	private String access_token;
	
	/**
	 * 过期秒数
	 */
	@Column(name="expires_in",nullable=false,jdbcType=JdbcType.INTEGER,comment="过期秒数")
	private Integer expires_in;
	
	/**
	 * 刷新tokent
	 */
	@Column(name="refresh_token",nullable=false,jdbcType=JdbcType.VARCHAR,comment="刷新tokent")
	private String refresh_token;
	
	/**
	 * 对应的用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="对应的用户ID")
	private Long userid;
	
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
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Date getAccess_date() {
		return access_date;
	}

	public void setAccess_date(Date access_date) {
		this.access_date = access_date;
	}
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
}