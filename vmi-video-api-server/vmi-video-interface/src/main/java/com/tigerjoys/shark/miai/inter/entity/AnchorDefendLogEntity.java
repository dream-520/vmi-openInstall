package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户守护变更记录表[t_anchor_defend_log] 表对应的实体类
 * @author shiming
 * @Date 2019-10-04 20:37:29
 *
 */
@Table(name="t_anchor_defend_log")
public class AnchorDefendLogEntity extends BaseEntity implements Serializable {

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
	 * 购买守护者
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="购买守护者")
	private Long userid;
	
	/**
	 * 守护的主播
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="守护的主播")
	private Long anchorid;
	
	/**
	 * 守护等级
	 */
	@Column(name="level",nullable=true,jdbcType=JdbcType.INTEGER,comment="守护等级")
	private Integer level;
	
	/**
	 * 守护变更记录类型 0 首次购买 1购买本类型延期 2 购买其他类型延期
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="守护变更记录类型 0 首次购买 1购买本类型延期 2 购买其他类型延期")
	private Integer type;
	
	/**
	 * 订单id
	 */
	@Column(name="orderid",nullable=true,jdbcType=JdbcType.BIGINT,comment="订单id")
	private Long orderid;
	
	/**
	 * 守护起始时间
	 */
	@Column(name="start_date",nullable=true,jdbcType=JdbcType.DATE,comment="守护起始时间")
	private Date start_date;
	
	/**
	 * 守护结束时间
	 */
	@Column(name="end_date",nullable=true,jdbcType=JdbcType.DATE,comment="守护结束时间")
	private Date end_date;
	
	/**
	 * 购买或者延长的天数
	 */
	@Column(name="days",nullable=true,jdbcType=JdbcType.INTEGER,comment="购买或者延长的天数")
	private Integer days;
	
	/**
	 * 创建日期
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建日期")
	private Date create_time;
	
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
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}