package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播评级体系[t_anchor_level_monitor] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-05 17:15:04
 *
 */
@Table(name="t_anchor_level_monitor")
public class AnchorLevelMonitorEntity extends BaseEntity implements Serializable {

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
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 主播id
	 */
	@Column(name="anchorid",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播id")
	private Long anchorid;
	
	/**
	 * 星级
	 */
	@Column(name="star",nullable=false,jdbcType=JdbcType.SMALLINT,comment="星级")
	private Integer star;
	
	/**
	 * 核查次数
	 */
	@Column(name="check_times",nullable=true,jdbcType=JdbcType.SMALLINT,comment="核查次数")
	private Integer check_times;
	
	/**
	 * 开始时间
	 */
	@Column(name="begin_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="开始时间")
	private Date begin_time;
	
	/**
	 * 结束时间
	 */
	@Column(name="end_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="结束时间")
	private Date end_time;
	
	/**
	 * 0 下线 1上线 2 人工升级
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 下线 1上线 2 人工升级")
	private Integer status;
	
	/**
	 * 主播状态星级更新时间
	 */
	@Column(name="anchor_update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="主播状态星级更新时间")
	private Date anchor_update_time;
	
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	public Integer getCheck_times() {
		return check_times;
	}

	public void setCheck_times(Integer check_times) {
		this.check_times = check_times;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getAnchor_update_time() {
		return anchor_update_time;
	}

	public void setAnchor_update_time(Date anchor_update_time) {
		this.anchor_update_time = anchor_update_time;
	}
	
}