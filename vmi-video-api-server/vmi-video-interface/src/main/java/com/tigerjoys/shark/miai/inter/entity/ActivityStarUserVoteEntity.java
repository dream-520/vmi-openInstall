package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  女神之星用户投注表[t_activity_star_user_vote] 表对应的实体类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Table(name="t_activity_star_user_vote")
public class ActivityStarUserVoteEntity extends BaseEntity implements Serializable {

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
	 * 投注人id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="投注人id")
	private Long userid;
	
	/**
	 * 投注期数
	 */
	@Column(name="issue_id",nullable=true,jdbcType=JdbcType.INTEGER,comment="投注期数")
	private Integer issue_id;
	
	/**
	 * 投注额度
	 */
	@Column(name="issue_vote",nullable=true,jdbcType=JdbcType.INTEGER,comment="投注额度")
	private Integer issue_vote;
	
	/**
	 * 投注的主播id
	 */
	@Column(name="issue_anchor",nullable=true,jdbcType=JdbcType.BIGINT,comment="投注的主播id")
	private Long issue_anchor;
	
	/**
	 * 投注时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="投注时间")
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
	
	public Long getIssue_anchor() {
		return issue_anchor;
	}

	public void setIssue_anchor(Long issue_anchor) {
		this.issue_anchor = issue_anchor;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}