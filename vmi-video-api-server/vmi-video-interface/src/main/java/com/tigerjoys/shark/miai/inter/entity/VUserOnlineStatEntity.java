package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_online_stat] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Table(name="t_v_user_online_stat")
public class VUserOnlineStatEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标志
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标志")
	private Long id;
	
	/**
	 * 用户id信息
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id信息")
	private Long userid;
	
	/**
	 * 用户昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户昵称")
	private String nickname;
	
	/**
	 * 用户等级
	 */
	@Column(name="level",nullable=true,jdbcType=JdbcType.TINYINT,comment="用户等级")
	private Integer level;
	
	/**
	 * 用户当前时间段的状态值
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="用户当前时间段的状态值")
	private Integer state;
	
	/**
	 * 是否是一分钟免费
	 */
	@Column(name="free",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否是一分钟免费")
	private Integer free;
	
	/**
	 * 用户当前时段的收入值
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="用户当前时段的收入值")
	private Integer money;
	
	/**
	 * 用户开启当前时段的时间
	 */
	@Column(name="startTime",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="用户开启当前时段的时间")
	private Date startTime;
	
	/**
	 * 用户结束当前时段的时间值
	 */
	@Column(name="endTime",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="用户结束当前时段的时间值")
	private Date endTime;
	
	/**
	 * 用户时段对应的当天时间
	 */
	@Column(name="createTime",nullable=false,jdbcType=JdbcType.DATE,comment="用户时段对应的当天时间")
	private Date createTime;
	
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
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}