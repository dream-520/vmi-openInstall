package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户签到记录表[t_user_sign_log] 表对应的实体类
 * @author lipeng
 * @Date 2019-12-06 18:10:43
 *
 */
@Table(name="t_user_sign_log")
public class UserSignLogEntity extends BaseEntity implements Serializable {

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
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 签到日期
	 */
	@Column(name="sign_time",nullable=false,jdbcType=JdbcType.VARCHAR,comment="签到日期")
	private String sign_time;
	
	/**
	 * 年月
	 */
	@Column(name="month",nullable=false,jdbcType=JdbcType.VARCHAR,comment="年月")
	private String month;
	
	/**
	 * 日
	 */
	@Column(name="day",nullable=false,jdbcType=JdbcType.TINYINT,comment="日")
	private Integer day;
	
	/**
	 * 连续签到天数
	 */
	@Column(name="days",nullable=false,jdbcType=JdbcType.INTEGER,comment="连续签到天数")
	private Integer days;
	
	/**
	 * 是否是补签:1是 0否
	 */
	@Column(name="sign_status",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否是补签:1是 0否")
	private Integer sign_status;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getSign_time() {
		return sign_time;
	}

	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
	
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public Integer getSign_status() {
		return sign_status;
	}

	public void setSign_status(Integer sign_status) {
		this.sign_status = sign_status;
	}
	
}