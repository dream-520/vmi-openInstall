package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用于标识首次购买vip后用户是否发送完对应消息的统计表[t_app_pay_vip_user] 表对应的实体类
 * @author shiming
 * @Date 2020-02-14 15:09:15
 *
 */
@Table(name="t_app_pay_vip_user")
public class AppPayVipUserEntity extends BaseEntity implements Serializable {

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
	 * 对应的状态值 0 标识还需要发送消息 1 标识发送消息结束
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="对应的状态值 0 标识还需要发送消息 1 标识发送消息结束")
	private Integer state;
	
	/**
	 * 发送消息创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="发送消息创建时间")
	private Date create_time;
	
	/**
	 * 发送完对应的消息的结束时间
	 */
	@Column(name="end_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="发送完对应的消息的结束时间")
	private Date end_time;
	
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
	
}