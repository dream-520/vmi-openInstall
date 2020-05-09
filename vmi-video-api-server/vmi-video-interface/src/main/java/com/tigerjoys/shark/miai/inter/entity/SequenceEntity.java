package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  ID生成器表[b_sequence] 表对应的实体类
 * @author chengang
 * @Date 2017-04-14 10:41:53
 *
 */
@Table(name="b_sequence")
public class SequenceEntity extends BaseEntity implements Serializable {

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
	 * 表名称
	 */
	@Column(name="entityname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="表名称")
	private String entityname;
	
	/**
	 * 当前ID序列
	 */
	@Column(name="current_value",nullable=false,jdbcType=JdbcType.BIGINT,comment="当前ID序列")
	private Long current_value;
	
	/**
	 * 每次随机增加范围
	 */
	@Column(name="increment",nullable=false,jdbcType=JdbcType.INTEGER,comment="每次随机增加范围")
	private Integer increment;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public Long getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(Long current_value) {
		this.current_value = current_value;
	}
	
	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
	
}