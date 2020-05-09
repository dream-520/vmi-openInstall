package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import org.apache.ibatis.type.JdbcType;

import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户好友表[t_user_friends] 表对应的实体类
 * @author chengang
 * @Date 2017-04-14 15:06:33
 *
 */
@Table(name="t_user_friends")
public class UserFriendsEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 好友ID
	 */
	@Column(name="friendid",nullable=false,jdbcType=JdbcType.BIGINT,comment="好友ID")
	private Long friendid;
	
	/**
	 * 是否互粉,0否,1是
	 */
	@Column(name="powder",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否互粉")
	private Integer powder;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public Long getFriendid() {
		return friendid;
	}

	public void setFriendid(Long friendid) {
		this.friendid = friendid;
	}

	public Integer getPowder() {
		return powder;
	}

	public void setPowder(Integer powder) {
		this.powder = powder;
	}
	
}