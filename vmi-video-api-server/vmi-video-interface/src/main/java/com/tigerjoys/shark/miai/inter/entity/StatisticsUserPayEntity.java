package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充值用户每日数据统计[t_statistics_user_pay] 表对应的实体类
 * @author lipeng
 * @Date 2019-06-24 20:42:38
 *
 */
@Table(name="t_statistics_user_pay")
public class StatisticsUserPayEntity extends BaseEntity implements Serializable {

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
	 * 用户昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户昵称")
	private String nickname;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 充值次数
	 */
	@Column(name="pay_stroke",nullable=false,jdbcType=JdbcType.BIGINT,comment="充值次数")
	private Long pay_stroke;
	
	/**
	 * 充值金额
	 */
	@Column(name="pay_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="充值金额")
	private Long pay_total;
	
	/**
	 * 余额
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="余额")
	private Long balance;
	
	/**
	 * 拨打次数
	 */
	@Column(name="dial_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="拨打次数")
	private Long dial_count;
	
	/**
	 * 接通次数
	 */
	@Column(name="call_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="接通次数")
	private Long call_count;
	
	/**
	 * 通话时长
	 */
	@Column(name="call_duration",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话时长")
	private Long call_duration;
	
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
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getPay_stroke() {
		return pay_stroke;
	}

	public void setPay_stroke(Long pay_stroke) {
		this.pay_stroke = pay_stroke;
	}
	
	public Long getPay_total() {
		return pay_total;
	}

	public void setPay_total(Long pay_total) {
		this.pay_total = pay_total;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Long getDial_count() {
		return dial_count;
	}

	public void setDial_count(Long dial_count) {
		this.dial_count = dial_count;
	}
	
	public Long getCall_count() {
		return call_count;
	}

	public void setCall_count(Long call_count) {
		this.call_count = call_count;
	}
	
	public Long getCall_duration() {
		return call_duration;
	}

	public void setCall_duration(Long call_duration) {
		this.call_duration = call_duration;
	}
	
}