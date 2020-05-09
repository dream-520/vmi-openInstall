package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  动态点赞记录[t_user_dynamic_favor] 表对应的实体类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Table(name="t_user_dynamic_favor")
public class UserDynamicFavorEntity extends BaseEntity implements Serializable {

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
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="userid")
	private Long userid;
	
	/**
	 * dynamic_id
	 */
	@Column(name="dynamic_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="dynamic_id")
	private Long dynamic_id;
	
	/**
	 * status
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.SMALLINT,comment="status")
	private Integer status;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
	private Date create_time;
	
	/**
	 * update_time
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="update_time")
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
	
	public Long getDynamic_id() {
		return dynamic_id;
	}

	public void setDynamic_id(Long dynamic_id) {
		this.dynamic_id = dynamic_id;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
}