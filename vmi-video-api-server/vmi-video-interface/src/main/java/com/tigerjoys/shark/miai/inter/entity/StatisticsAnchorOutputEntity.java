package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  付费用户表[t_statistics_anchor_output] 表对应的实体类
 * @author lipeng
 * @Date 2019-10-18 17:42:58
 *
 */
@Table(name="t_statistics_anchor_output")
public class StatisticsAnchorOutputEntity extends BaseEntity implements Serializable {

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
	 * 生成时间
	 */
	@Column(name="date",nullable=false,jdbcType=JdbcType.DATE,comment="生成时间")
	private Date date;
	
	/**
	 * 用户ID
	 */
	@Column(name="anchorid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long anchorid;
	
	/**
	 * 关联产出
	 */
	@Column(name="relevancyOutput",nullable=true,jdbcType=JdbcType.BIGINT,comment="关联产出")
	private Long relevancyOutput;
	
	/**
	 * 直接产出
	 */
	@Column(name="directOutput",nullable=true,jdbcType=JdbcType.BIGINT,comment="直接产出")
	private Long directOutput;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getRelevancyOutput() {
		return relevancyOutput;
	}

	public void setRelevancyOutput(Long relevancyOutput) {
		this.relevancyOutput = relevancyOutput;
	}
	
	public Long getDirectOutput() {
		return directOutput;
	}

	public void setDirectOutput(Long directOutput) {
		this.directOutput = directOutput;
	}
	
}