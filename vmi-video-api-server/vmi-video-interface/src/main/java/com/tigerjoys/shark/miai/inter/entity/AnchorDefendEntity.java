package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户守护主播表[t_anchor_defend] 表对应的实体类
 * @author shiming
 * @Date 2019-10-04 20:37:29
 *
 */
@Table(name="t_anchor_defend")
public class AnchorDefendEntity extends BaseEntity implements Serializable {

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
	 * 创建日期
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建日期")
	private Date create_time;
	
	/**
	 * 结束日期
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="结束日期")
	private Date update_time;
	
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
	
}