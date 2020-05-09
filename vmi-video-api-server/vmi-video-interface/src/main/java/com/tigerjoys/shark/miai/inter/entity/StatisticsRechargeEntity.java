package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_statistics_recharge] 表对应的实体类
 * @author shiming
 * @Date 2018-12-25 17:03:47
 *
 */
@Table(name="t_statistics_recharge")
public class StatisticsRechargeEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标识
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标识")
	private Long id;
	
	/**
	 * 累计总充值
	 */
	@Column(name="sum_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="累计总充值")
	private Integer sum_total;
	
	/**
	 * 累计总人次
	 */
	@Column(name="sum_total_count",nullable=true,jdbcType=JdbcType.INTEGER,comment="累计总人次")
	private Integer sum_total_count;
	
	/**
	 * 累计总人数
	 */
	@Column(name="sum_total_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="累计总人数")
	private Integer sum_total_people;
	
	/**
	 * 微信总充值
	 */
	@Column(name="wx_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="微信总充值")
	private Integer wx_total;
	
	/**
	 * 微信总人次
	 */
	@Column(name="wx_total_count",nullable=true,jdbcType=JdbcType.INTEGER,comment="微信总人次")
	private Integer wx_total_count;
	
	/**
	 * 微信总人数
	 */
	@Column(name="wx_total_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="微信总人数")
	private Integer wx_total_people;
	
	/**
	 * 支付宝总充值
	 */
	@Column(name="ali_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="支付宝总充值")
	private Integer ali_total;
	
	/**
	 * 支付宝总人次
	 */
	@Column(name="ali_total_count",nullable=true,jdbcType=JdbcType.INTEGER,comment="支付宝总人次")
	private Integer ali_total_count;
	
	/**
	 * 支付宝总人数
	 */
	@Column(name="ali_total_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="支付宝总人数")
	private Integer ali_total_people;
	
	/**
	 * 类型区分
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.SMALLINT,comment="类型区分")
	private Integer type;
	
	/**
	 * 时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.DATE,comment="时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getSum_total() {
		return sum_total;
	}

	public void setSum_total(Integer sum_total) {
		this.sum_total = sum_total;
	}
	
	public Integer getSum_total_count() {
		return sum_total_count;
	}

	public void setSum_total_count(Integer sum_total_count) {
		this.sum_total_count = sum_total_count;
	}
	
	public Integer getSum_total_people() {
		return sum_total_people;
	}

	public void setSum_total_people(Integer sum_total_people) {
		this.sum_total_people = sum_total_people;
	}
	
	public Integer getWx_total() {
		return wx_total;
	}

	public void setWx_total(Integer wx_total) {
		this.wx_total = wx_total;
	}
	
	public Integer getWx_total_count() {
		return wx_total_count;
	}

	public void setWx_total_count(Integer wx_total_count) {
		this.wx_total_count = wx_total_count;
	}
	
	public Integer getWx_total_people() {
		return wx_total_people;
	}

	public void setWx_total_people(Integer wx_total_people) {
		this.wx_total_people = wx_total_people;
	}
	
	public Integer getAli_total() {
		return ali_total;
	}

	public void setAli_total(Integer ali_total) {
		this.ali_total = ali_total;
	}
	
	public Integer getAli_total_count() {
		return ali_total_count;
	}

	public void setAli_total_count(Integer ali_total_count) {
		this.ali_total_count = ali_total_count;
	}
	
	public Integer getAli_total_people() {
		return ali_total_people;
	}

	public void setAli_total_people(Integer ali_total_people) {
		this.ali_total_people = ali_total_people;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}