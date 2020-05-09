package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  女神之星每期主播信息[t_activity_star_anchor] 表对应的实体类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Table(name="t_activity_star_anchor")
public class ActivityStarAnchorEntity extends BaseEntity implements Serializable {

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
	 * 本期主播id
	 */
	@Column(name="anchor_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="本期主播id")
	private Long anchor_id;
	
	/**
	 * 魅力值初始值
	 */
	@Column(name="charm_init",nullable=true,jdbcType=JdbcType.INTEGER,comment="魅力值初始值")
	private Integer charm_init;
	
	/**
	 * 魅力值当前值
	 */
	@Column(name="charm_current",nullable=true,jdbcType=JdbcType.INTEGER,comment="魅力值当前值")
	private Integer charm_current;
	
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
	
	public Long getAnchor_id() {
		return anchor_id;
	}

	public void setAnchor_id(Long anchor_id) {
		this.anchor_id = anchor_id;
	}
	
	public Integer getCharm_init() {
		return charm_init;
	}

	public void setCharm_init(Integer charm_init) {
		this.charm_init = charm_init;
	}
	
	public Integer getCharm_current() {
		return charm_current;
	}

	public void setCharm_current(Integer charm_current) {
		this.charm_current = charm_current;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}