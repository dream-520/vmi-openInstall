package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_a_m_day_total] 表对应的实体类
 * @author shiming
 * @Date 2018-11-15 14:03:19
 *
 */
@Table(name="t_a_m_day_total")
public class AMDayTotalEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 在线总人数
	 */
	@Column(name="online_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="在线总人数")
	private Integer online_num;
	
	/**
	 * 在聊总人数
	 */
	@Column(name="talking_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊总人数")
	private Integer talking_num;
	
	/**
	 * 在聊总分钟数
	 */
	@Column(name="talking_minute",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊总分钟数")
	private Integer talking_minute;
	
	/**
	 * 在聊总收入值
	 */
	@Column(name="talking_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊总收入值")
	private Integer talking_total;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.DATE,comment="创建时间")
	private Date create_time;
	
	/**
	 * 抓取的网页类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.SMALLINT,comment="抓取的网页类型")
	private Integer type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getOnline_num() {
		return online_num;
	}

	public void setOnline_num(Integer online_num) {
		this.online_num = online_num;
	}
	
	public Integer getTalking_num() {
		return talking_num;
	}

	public void setTalking_num(Integer talking_num) {
		this.talking_num = talking_num;
	}
	
	public Integer getTalking_minute() {
		return talking_minute;
	}

	public void setTalking_minute(Integer talking_minute) {
		this.talking_minute = talking_minute;
	}
	
	public Integer getTalking_total() {
		return talking_total;
	}

	public void setTalking_total(Integer talking_total) {
		this.talking_total = talking_total;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}