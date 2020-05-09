package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_online_stat_times] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Table(name="t_v_user_online_stat_times")
public class VUserOnlineStatTimesEntity extends BaseEntity implements Serializable {

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
	 * 时间段标示
	 */
	@Column(name="times",nullable=false,jdbcType=JdbcType.INTEGER,comment="时间段标示")
	private Integer times;
	
	/**
	 * 在线人数
	 */
	@Column(name="nums",nullable=false,jdbcType=JdbcType.INTEGER,comment="在线人数")
	private Integer nums;
	
	/**
	 * 对应的日期值
	 */
	@Column(name="createTime",nullable=false,jdbcType=JdbcType.DATE,comment="对应的日期值")
	private Date createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}