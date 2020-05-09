package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  女神之星活动期数管理表[t_activity_star_issue] 表对应的实体类
 * @author shiming
 * @Date 2019-08-29 14:05:31
 *
 */
@Table(name="t_activity_star_issue")
public class ActivityStarIssueEntity extends BaseEntity implements Serializable {

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
	 * 女神之星期数
	 */
	@Column(name="issue_number",nullable=true,jdbcType=JdbcType.INTEGER,comment="女神之星期数")
	private Integer issue_number;
	
	/**
	 * 本期中奖主播
	 */
	@Column(name="issue_anchor",nullable=true,jdbcType=JdbcType.BIGINT,comment="本期中奖主播")
	private Long issue_anchor;
	
	/**
	 * 本期参与总人数
	 */
	@Column(name="issue_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="本期参与总人数")
	private Integer issue_people;
	
	/**
	 * 本期投票小红花总数
	 */
	@Column(name="issue_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="本期投票小红花总数")
	private Integer issue_money;
	
	/**
	 * 状态信息 主要防止定时器挂了 不能生成对应的中奖纪录处理
	 */
	@Column(name="issue_state",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态信息 主要防止定时器挂了 不能生成对应的中奖纪录处理")
	private Integer issue_state;
	
	/**
	 * 展示的中奖人数
	 */
	@Column(name="show_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="展示的中奖人数")
	private Integer show_people;
	
	/**
	 * 展示的中奖钱数
	 */
	@Column(name="show_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="展示的中奖钱数")
	private Integer show_money;
	
	/**
	 * 本期开始时间
	 */
	@Column(name="begin_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="本期开始时间")
	private Date begin_time;
	
	/**
	 * 本期结束时间
	 */
	@Column(name="end_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="本期结束时间")
	private Date end_time;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getIssue_number() {
		return issue_number;
	}

	public void setIssue_number(Integer issue_number) {
		this.issue_number = issue_number;
	}
	
	public Long getIssue_anchor() {
		return issue_anchor;
	}

	public void setIssue_anchor(Long issue_anchor) {
		this.issue_anchor = issue_anchor;
	}
	
	public Integer getIssue_people() {
		return issue_people;
	}

	public void setIssue_people(Integer issue_people) {
		this.issue_people = issue_people;
	}
	
	public Integer getIssue_money() {
		return issue_money;
	}

	public void setIssue_money(Integer issue_money) {
		this.issue_money = issue_money;
	}
	
	public Integer getIssue_state() {
		return issue_state;
	}

	public void setIssue_state(Integer issue_state) {
		this.issue_state = issue_state;
	}
	
	public Integer getShow_people() {
		return show_people;
	}

	public void setShow_people(Integer show_people) {
		this.show_people = show_people;
	}
	
	public Integer getShow_money() {
		return show_money;
	}

	public void setShow_money(Integer show_money) {
		this.show_money = show_money;
	}
	
	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}