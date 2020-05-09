package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  达人VIP表[t_talent_vip] 表对应的实体类
 * @author chengang
 * @Date 2017-08-24 20:47:07
 *
 */
@Table(name="t_talent_vip")
public class TalentVipEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@Id(increment=false)
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 到期时间
	 */
	@Column(name="expire_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="到期时间")
	private Date expire_time;
	
	/**
	 * 过期是否被处理，如果新购买，必须将此值设置为0
	 */
	@Column(name="handler",nullable=false,jdbcType=JdbcType.TINYINT,comment="过期是否被处理")
	private int handler;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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
	
	public Date getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}
	
	public int getHandler() {
		return handler;
	}

	public void setHandler(int handler) {
		this.handler = handler;
	}

	@Override
	public Long getId() {
		return userid;
	}
}