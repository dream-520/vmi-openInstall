package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  城市级别对应表[t_app_area_city] 表对应的实体类
 * @author shiming
 * @Date 2019-07-26 14:49:39
 *
 */
@Table(name="t_app_area_city")
public class AppAreaCityEntity extends BaseEntity implements Serializable {

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
	 * 城市名称
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="城市名称")
	private String name;
	
	/**
	 * 百度code
	 */
	@Column(name="baidu_code",nullable=true,jdbcType=JdbcType.INTEGER,comment="百度code")
	private Integer baidu_code;
	
	/**
	 * 城市级别
	 */
	@Column(name="code",nullable=true,jdbcType=JdbcType.INTEGER,comment="城市级别")
	private Integer code;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
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
	
	public Integer getBaidu_code() {
		return baidu_code;
	}

	public void setBaidu_code(Integer baidu_code) {
		this.baidu_code = baidu_code;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}