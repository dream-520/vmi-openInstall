package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户购买vip后对应主播发送联系方式的记录表[t_app_pay_vip_user_anchor] 表对应的实体类
 * @author shiming
 * @Date 2020-02-14 16:20:17
 *
 */
@Table(name="t_app_pay_vip_user_anchor")
public class AppPayVipUserAnchorEntity extends BaseEntity implements Serializable {

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
	 * userid
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="userid")
	private Long userid;
	
	/**
	 * anchorid
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="anchorid")
	private Long anchorid;
	
	/**
	 * 发送消息的索引
	 */
	@Column(name="msg_index",nullable=true,jdbcType=JdbcType.INTEGER,comment="发送消息的索引")
	private Integer msg_index;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
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
	
	public Integer getMsg_index() {
		return msg_index;
	}

	public void setMsg_index(Integer msg_index) {
		this.msg_index = msg_index;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}