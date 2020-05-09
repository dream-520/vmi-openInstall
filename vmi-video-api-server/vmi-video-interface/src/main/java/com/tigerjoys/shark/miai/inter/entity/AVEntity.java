package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_a_v] 表对应的实体类
 * @author shiming
 * @Date 2018-11-06 16:11:06
 *
 */
@Table(name="t_a_v")
public class AVEntity extends BaseEntity implements Serializable {

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
	 * nickname
	 */
	@Column(name="nickname",nullable=true,jdbcType=JdbcType.VARCHAR,comment="nickname")
	private String nickname;
	
	/**
	 * type
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.SMALLINT,comment="type")
	private Integer type;
	
	/**
	 * data
	 */
	@Column(name="data",nullable=true,jdbcType=JdbcType.VARCHAR,comment="data")
	private String data;
	
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
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}