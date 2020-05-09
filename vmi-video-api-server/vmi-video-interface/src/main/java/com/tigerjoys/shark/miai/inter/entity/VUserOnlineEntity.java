package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_online] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Table(name="t_v_user_online")
public class VUserOnlineEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标示")
	private Long id;
	
	/**
	 * userid
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="userid")
	private Long userid;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 自我介绍
	 */
	@Column(name="topic",nullable=false,jdbcType=JdbcType.VARCHAR,comment="自我介绍")
	private String topic;
	
	/**
	 * 每分钟消耗的V币值
	 */
	@Column(name="vcoinPerMinute",nullable=false,jdbcType=JdbcType.INTEGER,comment="每分钟消耗的V币值")
	private Integer vcoinPerMinute;
	
	/**
	 * 用户当前的星级
	 */
	@Column(name="level",nullable=false,jdbcType=JdbcType.INTEGER,comment="用户当前的星级")
	private Integer level;
	
	/**
	 * 用户的当前状态
	 */
	@Column(name="online",nullable=false,jdbcType=JdbcType.INTEGER,comment="用户的当前状态")
	private Integer online;
	
	/**
	 * 头像路径
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="头像路径")
	private String photo;
	
	/**
	 * 是否一分钟免费
	 */
	@Column(name="isOneFree",nullable=false,jdbcType=JdbcType.INTEGER,comment="是否一分钟免费")
	private Integer isOneFree;
	
	/**
	 * 获取当前用户的时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="获取当前用户的时间")
	private Date create_time;
	
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
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Integer getVcoinPerMinute() {
		return vcoinPerMinute;
	}

	public void setVcoinPerMinute(Integer vcoinPerMinute) {
		this.vcoinPerMinute = vcoinPerMinute;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Integer getIsOneFree() {
		return isOneFree;
	}

	public void setIsOneFree(Integer isOneFree) {
		this.isOneFree = isOneFree;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}