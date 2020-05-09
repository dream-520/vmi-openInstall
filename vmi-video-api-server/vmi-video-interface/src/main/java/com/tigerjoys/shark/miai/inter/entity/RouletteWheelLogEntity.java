package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  轮盘抽奖记录[t_roulette_wheel_log] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-07-25 16:40:39
 *
 */
@Table(name="t_roulette_wheel_log")
public class RouletteWheelLogEntity extends BaseEntity implements Serializable {

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
	 * 是否是免费抽奖
	 */
	@Column(name="free",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否是免费抽奖")
	private Integer free;
	
	/**
	 * 本次抽奖花费的钻石值
	 */
	@Column(name="cost",nullable=true,jdbcType=JdbcType.INTEGER,comment="本次抽奖花费的钻石值")
	private Integer cost;
	
	/**
	 * 本次抽奖奖励的钻石值
	 */
	@Column(name="award",nullable=true,jdbcType=JdbcType.INTEGER,comment="本次抽奖奖励的钻石值")
	private Integer award;
	
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
	
	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getAward() {
		return award;
	}

	public void setAward(Integer award) {
		this.award = award;
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