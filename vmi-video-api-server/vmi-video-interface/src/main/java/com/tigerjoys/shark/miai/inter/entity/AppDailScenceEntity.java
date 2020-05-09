package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_app_dail_scence] 表对应的实体类
 * @author shiming
 * @Date 2019-12-30 11:15:35
 *
 */
@Table(name="t_app_dail_scence")
public class AppDailScenceEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 主播id
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播id")
	private Long anchorid;
	
	/**
	 * 对应的场景id
	 */
	@Column(name="scence",nullable=true,jdbcType=JdbcType.INTEGER,comment="对应的场景id")
	private Integer scence;
	
	/**
	 * 已经发送到的场景中对话的id
	 */
	@Column(name="scence_index",nullable=true,jdbcType=JdbcType.INTEGER,comment="已经发送到的场景中对话的id")
	private Integer scence_index;
	
	/**
	 * 已发送场景消息的类型
	 */
	@Column(name="scence_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="已发送场景消息的类型")
	private Integer scence_type;
	
	/**
	 * 用于标识图片类型消息 是否能够发送下一条标识
	 */
	@Column(name="send",nullable=true,jdbcType=JdbcType.TINYINT,comment="用于标识图片类型消息 是否能够发送下一条标识")
	private Integer send;
	
	/**
	 * 本场景的状态 0 尚未完成 1 以完成 2 用户充值需要终止
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="本场景的状态 0 尚未完成 1 以完成 2 用户充值需要终止")
	private Integer state;
	
	/**
	 * 发送本次场景对话的时间
	 */
	@Column(name="send_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="发送本次场景对话的时间")
	private Date send_time;
	
	/**
	 * 触发本次场景的时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="触发本次场景的时间")
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
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Integer getScence() {
		return scence;
	}

	public void setScence(Integer scence) {
		this.scence = scence;
	}
	
	public Integer getScence_index() {
		return scence_index;
	}

	public void setScence_index(Integer scence_index) {
		this.scence_index = scence_index;
	}
	
	public Integer getScence_type() {
		return scence_type;
	}

	public void setScence_type(Integer scence_type) {
		this.scence_type = scence_type;
	}
	
	public Integer getSend() {
		return send;
	}

	public void setSend(Integer send) {
		this.send = send;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}