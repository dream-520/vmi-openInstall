package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  首页弹窗log表[t_home_pop_log] 表对应的实体类
 * @author liuman
 * @Date 2017-12-21 18:21:57
 *
 */
@Table(name="t_home_pop_log")
public class HomePopLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 弹窗ID
	 */
	@Column(name="popid",nullable=false,jdbcType=JdbcType.BIGINT,comment="弹窗ID")
	private Long popid;
	
	/**
	 * 设备ID
	 */
	@Column(name="client_id",nullable=false,jdbcType=JdbcType.VARCHAR,comment="设备ID")
	private String client_id;
	
	/**
	 * 查看次数
	 */
	@Column(name="count",nullable=false,jdbcType=JdbcType.INTEGER,comment="查看次数")
	private Integer count;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getPopid() {
		return popid;
	}

	public void setPopid(Long popid) {
		this.popid = popid;
	}
	
	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}