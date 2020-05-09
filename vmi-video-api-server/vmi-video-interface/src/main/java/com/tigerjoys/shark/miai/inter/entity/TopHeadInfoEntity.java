package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_top_head_info] 表对应的实体类
 * @author yangjunming
 * @Date 2018-04-28 15:41:42
 *
 */
@Table(name="t_top_head_info")
public class TopHeadInfoEntity extends BaseEntity implements Serializable {

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
	 * 用户名 可以为空
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户名 可以为空")
	private String name;
	
	/**
	 * 头条信息
	 */
	@Column(name="info",nullable=true,jdbcType=JdbcType.VARCHAR,comment="头条信息")
	private String info;
	
	/**
	 * 状态 0 关闭  1 开启
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 0 关闭  1 开启")
	private Integer status;
	
	/**
	 * 头条分类 1 生产 2提测
	 */
	@Column(name="top_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="头条分类 1 生产 2提测")
	private Integer top_type;
	
	/**
	 * 时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="时间")
	private Date create_time;
	
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
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getTop_type() {
		return top_type;
	}

	public void setTop_type(Integer top_type) {
		this.top_type = top_type;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}