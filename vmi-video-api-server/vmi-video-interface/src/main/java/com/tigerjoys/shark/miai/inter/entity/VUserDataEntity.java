package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_data] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Table(name="t_v_user_data")
public class VUserDataEntity extends BaseEntity implements Serializable {

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
	 * 头像路径
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="头像路径")
	private String photo;
	
	/**
	 * 获取当前用户的时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="获取当前用户的时间")
	private Date create_time;
	
	/**
	 * 用于记录访问本用户的时间
	 */
	@Column(name="visit",nullable=true,jdbcType=JdbcType.DATE,comment="用于记录访问本用户的时间")
	private Date visit;
	
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getVisit() {
		return visit;
	}

	public void setVisit(Date visit) {
		this.visit = visit;
	}
	
}