package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户音视频体验收益[t_user_vchat_experience_income_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-10 12:12:46
 *
 */
@Table(name="t_user_vchat_experience_income_log")
public class UserVchatExperienceIncomeLogEntity extends BaseEntity implements Serializable {

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
	 * 通话订单Id
	 */
	@Column(name="orderId",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话订单Id")
	private Long orderId;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 主播ID
	 */
	@Column(name="anchorid",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播ID")
	private Long anchorid;
	
	/**
	 * 用户Id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户Id")
	private Long userid;
	
	/**
	 * 收益
	 */
	@Column(name="price",nullable=false,jdbcType=JdbcType.INTEGER,comment="收益")
	private Integer price;
	
	/**
	 * 1 音频 2 视频
	 */
	@Column(name="av_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="1 音频 2 视频")
	private Integer av_type;
	
	/**
	 * 0 未超过30秒 1 超过30S
	 */
	@Column(name="duration_falg",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 未超过30秒 1 超过30S")
	private Integer duration_falg;
	
	/**
	 * 状态 0 未结算 1结算
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态 0 未结算 1结算")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getAv_type() {
		return av_type;
	}

	public void setAv_type(Integer av_type) {
		this.av_type = av_type;
	}
	
	public Integer getDuration_falg() {
		return duration_falg;
	}

	public void setDuration_falg(Integer duration_falg) {
		this.duration_falg = duration_falg;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}