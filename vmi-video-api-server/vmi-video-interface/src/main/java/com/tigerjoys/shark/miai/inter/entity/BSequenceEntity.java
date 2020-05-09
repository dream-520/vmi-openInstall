package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_b_sequence] 表对应的实体类
 * @author yangjunming
 * @Date 2018-09-18 11:24:01
 *
 */
@Table(name="t_b_sequence")
public class BSequenceEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id(increment=false)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * name
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="name")
	private String name;
	
	/**
	 * current_value
	 */
	@Column(name="current_value",nullable=true,jdbcType=JdbcType.BIGINT,comment="current_value")
	private Long current_value;
	
	/**
	 * min_value
	 */
	@Column(name="min_value",nullable=true,jdbcType=JdbcType.BIGINT,comment="min_value")
	private Long min_value;
	
	/**
	 * max_value
	 */
	@Column(name="max_value",nullable=true,jdbcType=JdbcType.BIGINT,comment="max_value")
	private Long max_value;
	
	/**
	 * setp_value
	 */
	@Column(name="setp_value",nullable=true,jdbcType=JdbcType.BIGINT,comment="setp_value")
	private Long setp_value;
	
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
	
	public Long getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(Long current_value) {
		this.current_value = current_value;
	}
	
	public Long getMin_value() {
		return min_value;
	}

	public void setMin_value(Long min_value) {
		this.min_value = min_value;
	}
	
	public Long getMax_value() {
		return max_value;
	}

	public void setMax_value(Long max_value) {
		this.max_value = max_value;
	}
	
	public Long getSetp_value() {
		return setp_value;
	}

	public void setSetp_value(Long setp_value) {
		this.setp_value = setp_value;
	}
	
}