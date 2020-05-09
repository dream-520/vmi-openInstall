package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  每日SQL数据[t_app_dailysql] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-25 15:33:24
 *
 */
@Table(name="t_app_dailysql")
public class AppDailysqlEntity extends BaseEntity implements Serializable {

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
	 * 标签名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标签名称")
	private String name;
	
	/**
	 * 动作sql
	 */
	@Column(name="action_sql",nullable=false,jdbcType=JdbcType.VARCHAR,comment="动作sql")
	private String action_sql;
	
	/**
	 * 标签状态(1可用 -9不可用)
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.SMALLINT,comment="标签状态(1可用 -9不可用)")
	private Integer state;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAction_sql() {
		return action_sql;
	}

	public void setAction_sql(String action_sql) {
		this.action_sql = action_sql;
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}