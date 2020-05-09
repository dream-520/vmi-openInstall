package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_statistics_user_total] 表对应的实体类
 * @author shiming
 * @Date 2017-06-09 11:46:41
 *
 */
@Table(name="t_statistics_user_total")
public class StatisticsUserTotalEntity extends BaseEntity implements Serializable {

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
	 * 新增或者累计数据生成的时间
	 */
	@Column(name="date",nullable=false,jdbcType=JdbcType.DATE,comment="新增或者累计数据生成的时间")
	private Date date;
	
	/**
	 * 微信注册人数
	 */
	@Column(name="wx_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="微信注册人数")
	private Long wx_total;
	
	/**
	 * qq注册人数
	 */
	@Column(name="qq_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="qq注册人数")
	private Long qq_total;
	
	/**
	 * 手机注册人数
	 */
	@Column(name="mobile_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="手机注册人数")
	private Long mobile_total;
	
	/**
	 * 其他注册人数
	 */
	@Column(name="other_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="其他注册人数")
	private Long other_total;
	
	/**
	 * 总注册人数
	 */
	@Column(name="total",nullable=false,jdbcType=JdbcType.BIGINT,comment="总注册人数")
	private Long total;
	
	/**
	 * 数据类型 0为注册用户  1为累计用户
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="数据类型 0为注册用户  1为累计用户")
	private Integer type;
	
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
	
	public Long getWx_total() {
		return wx_total;
	}

	public void setWx_total(Long wx_total) {
		this.wx_total = wx_total;
	}
	
	public Long getQq_total() {
		return qq_total;
	}

	public void setQq_total(Long qq_total) {
		this.qq_total = qq_total;
	}
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getMobile_total() {
		return mobile_total;
	}

	public void setMobile_total(Long mobile_total) {
		this.mobile_total = mobile_total;
	}

	public Long getOther_total() {
		return other_total;
	}

	public void setOther_total(Long other_total) {
		this.other_total = other_total;
	}
	
}