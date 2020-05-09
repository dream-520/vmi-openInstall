package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_activity_star_user_prize] 表对应的实体类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Table(name="t_activity_star_user_prize")
public class ActivityStarUserPrizeEntity extends BaseEntity implements Serializable {

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
	 * 中奖人id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="中奖人id")
	private Long userid;
	
	/**
	 * 中奖期数
	 */
	@Column(name="issue_id",nullable=true,jdbcType=JdbcType.INTEGER,comment="中奖期数")
	private Integer issue_id;
	
	/**
	 * 本期投注总金额
	 */
	@Column(name="issue_vote",nullable=true,jdbcType=JdbcType.INTEGER,comment="本期投注总金额")
	private Integer issue_vote;
	
	/**
	 * 本期中奖额度
	 */
	@Column(name="issue_prize",nullable=true,jdbcType=JdbcType.INTEGER,comment="本期中奖额度")
	private Integer issue_prize;
	
	/**
	 * 是否已经显示过中奖信息
	 */
	@Column(name="is_show",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否已经显示过中奖信息")
	private Integer is_show;
	
	/**
	 * 中奖纪录创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="中奖纪录创建时间")
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
	
	public Integer getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(Integer issue_id) {
		this.issue_id = issue_id;
	}
	
	public Integer getIssue_vote() {
		return issue_vote;
	}

	public void setIssue_vote(Integer issue_vote) {
		this.issue_vote = issue_vote;
	}
	
	public Integer getIssue_prize() {
		return issue_prize;
	}

	public void setIssue_prize(Integer issue_prize) {
		this.issue_prize = issue_prize;
	}
	
	public Integer getIs_show() {
		return is_show;
	}

	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}