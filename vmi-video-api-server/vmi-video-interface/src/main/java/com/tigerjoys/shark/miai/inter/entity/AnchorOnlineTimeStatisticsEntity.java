package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播在线情况统计表[t_anchor_online_time_statistics] 表对应的实体类
 * @author shiming
 * @Date 2019-09-03 17:00:45
 *
 */
@Table(name="t_anchor_online_time_statistics")
public class AnchorOnlineTimeStatisticsEntity extends BaseEntity implements Serializable {

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
	 * 主播id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播id")
	private Long userid;
	
	/**
	 * 统计类型 1 小时级别 2 天级别 3 周级别 4 月级别
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="统计类型 1 小时级别 2 天级别 3 周级别 4 月级别")
	private Integer type;
	
	/**
	 * 小时值
	 */
	@Column(name="hours",nullable=true,jdbcType=JdbcType.INTEGER,comment="小时值")
	private Integer hours;
	
	/**
	 * 在线时长
	 */
	@Column(name="online",nullable=true,jdbcType=JdbcType.INTEGER,comment="在线时长")
	private Integer online;
	
	/**
	 * 在聊时长
	 */
	@Column(name="talk",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊时长")
	private Integer talk;
	
	/**
	 * 勿扰时长
	 */
	@Column(name="distrub",nullable=true,jdbcType=JdbcType.INTEGER,comment="勿扰时长")
	private Integer distrub;
	
	/**
	 * 日期值
	 */
	@Column(name="check_date",nullable=true,jdbcType=JdbcType.DATE,comment="日期值")
	private Date check_date;
	
	/**
	 * 本时段连续在线10分钟标识
	 */
	@Column(name="series",nullable=true,jdbcType=JdbcType.TINYINT,comment="本时段连续在线10分钟标识")
	private Integer series;
	
	/**
	 * 临时记录本时段连续在线时长
	 */
	@Column(name="times",nullable=true,jdbcType=JdbcType.INTEGER,comment="临时记录本时段连续在线时长")
	private Integer times;
	
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}
	
	public Integer getTalk() {
		return talk;
	}

	public void setTalk(Integer talk) {
		this.talk = talk;
	}
	
	public Integer getDistrub() {
		return distrub;
	}

	public void setDistrub(Integer distrub) {
		this.distrub = distrub;
	}
	
	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	
	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}
	
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
}