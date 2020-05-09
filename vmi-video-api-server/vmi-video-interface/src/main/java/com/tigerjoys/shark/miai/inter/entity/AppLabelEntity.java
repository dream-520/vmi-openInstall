package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  app使用的标签库(形象标签和用户评论标签)[t_app_label] 表对应的实体类
 * @author shiming
 * @Date 2018-10-29 11:27:02
 *
 */
@Table(name="t_app_label")
public class AppLabelEntity extends BaseEntity implements Serializable {

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
	 * 标签颜色值
	 */
	@Column(name="color",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标签颜色值")
	private String color;
	
	/**
	 * 标签类型(1形象标签 2评论标签)
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="标签类型(1形象标签 2评论标签)")
	private Integer type;
	
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
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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