package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  app补助用户记录表[t_app_allowance_user] 表对应的实体类
 * @author shiming
 * @Date 2019-07-10 17:41:27
 *
 */
@Table(name="t_app_allowance_user")
public class AppAllowanceUserEntity extends BaseEntity implements Serializable {

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
	 * 获取补助的用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="获取补助的用户id")
	private Long userid;
	
	/**
	 * 发放补助的时间
	 */
	@Column(name="allowance_date",nullable=true,jdbcType=JdbcType.DATE,comment="发放补助的时间")
	private Date allowance_date;
	
	/**
	 * 记录生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="记录生成时间")
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
	
	public Date getAllowance_date() {
		return allowance_date;
	}

	public void setAllowance_date(Date allowance_date) {
		this.allowance_date = allowance_date;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}