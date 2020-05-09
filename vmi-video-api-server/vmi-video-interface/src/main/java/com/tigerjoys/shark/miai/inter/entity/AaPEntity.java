package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_aa_p] 表对应的实体类
 * @author shiming
 * @Date 2018-11-02 08:51:24
 *
 */
@Table(name="t_aa_p")
public class AaPEntity extends BaseEntity implements Serializable {

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
	 * 数量值
	 */
	@Column(name="num",nullable=true,jdbcType=JdbcType.INTEGER,comment="数量值")
	private Integer num;
	
	/**
	 * 存储的数据
	 */
	@Column(name="data",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="存储的数据")
	private String data;
	
	/**
	 * 类型(1风景 2视觉)
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.INTEGER,comment="类型(1风景 2视觉)")
	private Integer type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}