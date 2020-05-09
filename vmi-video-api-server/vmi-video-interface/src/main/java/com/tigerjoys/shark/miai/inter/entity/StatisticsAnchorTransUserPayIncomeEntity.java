package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_statistics_anchor_trans_user_pay_income] 表对应的实体类
 * @author shiming
 * @Date 2019-07-23 15:32:31
 *
 */
@Table(name="t_statistics_anchor_trans_user_pay_income")
public class StatisticsAnchorTransUserPayIncomeEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id(increment=true)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 核查日期
	 */
	@Column(name="check_date",nullable=true,jdbcType=JdbcType.DATE,comment="核查日期")
	private Date check_date;
	
	/**
	 * anchorid
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="anchorid")
	private Long anchorid;
	
	/**
	 * userid
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="userid")
	private Long userid;
	
	/**
	 * orderid
	 */
	@Column(name="orderid",nullable=true,jdbcType=JdbcType.BIGINT,comment="orderid")
	private Long orderid;
	
	/**
	 * money
	 */
	@Column(name="money",nullable=true,jdbcType=JdbcType.INTEGER,comment="money")
	private Integer money;
	
	/**
	 * prize
	 */
	@Column(name="prize",nullable=true,jdbcType=JdbcType.INTEGER,comment="prize")
	private Integer prize;
	
	/**
	 * 1 主动 2被动
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="1 主动 2被动")
	private Integer type;
	
	/**
	 * 0 未结算  1 结算
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 未结算  1 结算")
	private Integer status;
	
	/**
	 * 记录对应的渠道信息
	 */
	@Column(name="channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="记录对应的渠道信息")
	private String channel;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 结算时间
	 */
	@Column(name="settle_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="结算时间")
	private Date settle_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getPrize() {
		return prize;
	}

	public void setPrize(Integer prize) {
		this.prize = prize;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getSettle_time() {
		return settle_time;
	}

	public void setSettle_time(Date settle_time) {
		this.settle_time = settle_time;
	}
	
}