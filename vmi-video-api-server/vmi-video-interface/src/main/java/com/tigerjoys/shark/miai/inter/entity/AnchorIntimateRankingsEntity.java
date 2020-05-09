package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播亲密排行榜[t_anchor_intimate_rankings] 表对应的实体类
 * @author yangjunming
 * @Date 2018-10-30 10:16:48
 *
 */
@Table(name="t_anchor_intimate_rankings")
public class AnchorIntimateRankingsEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 主播用户ID
	 */
	@Column(name="anchor_userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播用户ID")
	private Long anchor_userid;
	
	/**
	 * 普通用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="普通用户ID")
	private Long userid;
	
	/**
	 * 总收入
	 */
	@Column(name="total_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="总收入")
	private Integer total_money;
	
	/**
	 * update_time
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="update_time")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAnchor_userid() {
		return anchor_userid;
	}

	public void setAnchor_userid(Long anchor_userid) {
		this.anchor_userid = anchor_userid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Integer total_money) {
		this.total_money = total_money;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}