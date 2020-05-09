package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充能量自定义分类表[t_energy_custom_category] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Table(name="t_energy_custom_category")
public class EnergyCustomCategoryEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 手机价格区间名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机价格区间名称")
	private String name;
	
	/**
	 * 一元首冲，1开，0关
	 */
	@Column(name="first",nullable=false,jdbcType=JdbcType.TINYINT,comment="一元首冲，1开，0关")
	private Integer first;
	
	/**
	 * 状态，1正常，0停用，-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态，1正常，0停用，-9删除")
	private Integer status;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}