package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  App假排行榜数据[t_app_ranking] 表对应的实体类
 * @author shiming
 * @Date 2019-07-16 18:21:38
 *
 */
@Table(name="t_app_ranking")
public class AppRankingEntity extends BaseEntity implements Serializable {

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
	 * 用户id标识
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id标识")
	private Long userid;
	
	/**
	 * 用户类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="用户类型")
	private Integer type;
	
	/**
	 * 用户等级
	 */
	@Column(name="level",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户等级")
	private Integer level;
	
	/**
	 * 魅力值或者财富值
	 */
	@Column(name="contribution",nullable=true,jdbcType=JdbcType.INTEGER,comment="魅力值或者财富值")
	private Integer contribution;
	
	/**
	 * 显示状态  1 正常 -1 下架 -9 删除
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="显示状态  1 正常 -1 下架 -9 删除")
	private Integer status;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getContribution() {
		return contribution;
	}

	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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