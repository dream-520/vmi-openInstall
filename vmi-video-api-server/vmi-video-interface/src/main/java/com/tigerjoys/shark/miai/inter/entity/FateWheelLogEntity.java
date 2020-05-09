package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  姻缘碰碰抽奖记录[t_fate_wheel_log] 表对应的实体类
 * @author shiming
 * @Date 2018-08-08 17:42:49
 *
 */
@Table(name="t_fate_wheel_log")
public class FateWheelLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id(increment=false)
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
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 抽中有联系方式的机器人的id
	 */
	@Column(name="robot_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="抽中有联系方式的机器人的id")
	private Long robot_id;
	
	/**
	 * 轮盘选项Id
	 */
	@Column(name="wheel_id",nullable=false,jdbcType=JdbcType.INTEGER,comment="轮盘选项Id")
	private Integer wheel_id;
	
	/**
	 * 描述
	 */
	@Column(name="description",nullable=false,jdbcType=JdbcType.VARCHAR,comment="描述")
	private String description;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getRobot_id() {
		return robot_id;
	}

	public void setRobot_id(Long robot_id) {
		this.robot_id = robot_id;
	}
	
	public Integer getWheel_id() {
		return wheel_id;
	}

	public void setWheel_id(Integer wheel_id) {
		this.wheel_id = wheel_id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}