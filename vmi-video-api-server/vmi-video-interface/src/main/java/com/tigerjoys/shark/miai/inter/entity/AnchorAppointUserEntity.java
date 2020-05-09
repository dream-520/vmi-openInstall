package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户约会信息记录表[t_anchor_appoint_user] 表对应的实体类
 * @author shiming
 * @Date 2020-01-07 20:00:05
 *
 */
@Table(name="t_anchor_appoint_user")
public class AnchorAppointUserEntity extends BaseEntity implements Serializable {

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
	 * 约会id
	 */
	@Column(name="appointid",nullable=true,jdbcType=JdbcType.BIGINT,comment="约会id")
	private Long appointid;
	
	/**
	 * 状态信息 0 预约中 1 预约已经过期
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态信息 0 预约中 1 预约已经过期")
	private Integer state;
	
	/**
	 * 预约时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="预约时间")
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
	
	public Long getAppointid() {
		return appointid;
	}

	public void setAppointid(Long appointid) {
		this.appointid = appointid;
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
	
}