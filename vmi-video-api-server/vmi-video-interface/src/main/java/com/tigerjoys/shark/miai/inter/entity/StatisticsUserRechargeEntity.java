package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充值统计表[t_statistics_user_recharge] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-09-13 19:30:10
 *
 */
@Table(name="t_statistics_user_recharge")
public class StatisticsUserRechargeEntity extends BaseEntity implements Serializable {

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
	 * 日期
	 */
	@Column(name="date",nullable=false,jdbcType=JdbcType.DATE,comment="日期")
	private Date date;
	
	/**
	 * 微信新增充值金额
	 */
	@Column(name="wx_increment",nullable=false,jdbcType=JdbcType.DECIMAL,comment="微信新增充值金额")
	private Double wx_increment;
	
	/**
	 * 支付宝新增充值金额
	 */
	@Column(name="ali_increment",nullable=false,jdbcType=JdbcType.DECIMAL,comment="支付宝新增充值金额")
	private Double ali_increment;
	
	/**
	 * 苹果新增充值金额
	 */
	@Column(name="iap_increment",nullable=false,jdbcType=JdbcType.DECIMAL,comment="苹果新增充值金额")
	private Double iap_increment;
	
	/**
	 * 新增总充值金额
	 */
	@Column(name="increment",nullable=false,jdbcType=JdbcType.DECIMAL,comment="新增总充值金额")
	private Double increment;
	
	/**
	 * 微信充值金额
	 */
	@Column(name="wx_total",nullable=false,jdbcType=JdbcType.DECIMAL,comment="微信充值金额")
	private Double wx_total;
	
	/**
	 * 支付宝充值金额
	 */
	@Column(name="ali_total",nullable=false,jdbcType=JdbcType.DECIMAL,comment="支付宝充值金额")
	private Double ali_total;
	
	/**
	 * 苹果充值金额
	 */
	@Column(name="iap_total",nullable=false,jdbcType=JdbcType.DECIMAL,comment="苹果充值金额")
	private Double iap_total;
	
	/**
	 * 总充值金额
	 */
	@Column(name="total",nullable=false,jdbcType=JdbcType.DECIMAL,comment="总充值金额")
	private Double total;
	
	/**
	 * 新增充值人次
	 */
	@Column(name="rechargers_increment",nullable=false,jdbcType=JdbcType.DECIMAL,comment="新增充值人次")
	private Double rechargers_increment;
	
	/**
	 * 总充值人次
	 */
	@Column(name="rechargers_total",nullable=false,jdbcType=JdbcType.DECIMAL,comment="总充值人次")
	private Double rechargers_total;
	
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
	
	public Double getWx_increment() {
		return wx_increment;
	}

	public void setWx_increment(Double wx_increment) {
		this.wx_increment = wx_increment;
	}
	
	public Double getAli_increment() {
		return ali_increment;
	}

	public void setAli_increment(Double ali_increment) {
		this.ali_increment = ali_increment;
	}
	
	public Double getIap_increment() {
		return iap_increment;
	}

	public void setIap_increment(Double iap_increment) {
		this.iap_increment = iap_increment;
	}
	
	public Double getIncrement() {
		return increment;
	}

	public void setIncrement(Double increment) {
		this.increment = increment;
	}
	
	public Double getWx_total() {
		return wx_total;
	}

	public void setWx_total(Double wx_total) {
		this.wx_total = wx_total;
	}
	
	public Double getAli_total() {
		return ali_total;
	}

	public void setAli_total(Double ali_total) {
		this.ali_total = ali_total;
	}
	
	public Double getIap_total() {
		return iap_total;
	}

	public void setIap_total(Double iap_total) {
		this.iap_total = iap_total;
	}
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Double getRechargers_increment() {
		return rechargers_increment;
	}

	public void setRechargers_increment(Double rechargers_increment) {
		this.rechargers_increment = rechargers_increment;
	}
	
	public Double getRechargers_total() {
		return rechargers_total;
	}

	public void setRechargers_total(Double rechargers_total) {
		this.rechargers_total = rechargers_total;
	}
	
}