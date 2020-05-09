package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户一键预约详情表[t_anchor_super_subscribe_detail] 表对应的实体类
 * @author shiming
 * @Date 2019-11-18 14:45:37
 *
 */
@Table(name="t_anchor_super_subscribe_detail")
public class AnchorSuperSubscribeDetailEntity extends BaseEntity implements Serializable {

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
	 * 发起预约的用户
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="发起预约的用户")
	private Long userid;
	
	/**
	 * 预约的主播
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="预约的主播")
	private Long anchorid;
	
	/**
	 * 预约序号
	 */
	@Column(name="subscribe",nullable=true,jdbcType=JdbcType.BIGINT,comment="预约序号")
	private Long subscribe;
	
	/**
	 * 预约状态  0 预约中  1 预约失败 2 预约成功
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="预约状态  0 预约中  1 预约失败 2 预约成功")
	private Integer state;
	
	/**
	 * 预约创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="预约创建时间")
	private Date create_time;
	
	/**
	 * 预约结束时间
	 */
	@Column(name="end_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="预约结束时间")
	private Date end_time;
	
	/**
	 * 条目更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="条目更新时间")
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
	
	public Long getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Long subscribe) {
		this.subscribe = subscribe;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}