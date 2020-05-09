package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_mei_user_dynamic] 表对应的实体类
 * @author chengang
 * @Date 2017-12-28 16:59:17
 *
 */
@Table(name="t_mei_user_dynamic")
public class MeiUserDynamicEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="uid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long uid;
	
	/**
	 * type
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="type")
	private Integer type;
	
	/**
	 * 内容
	 */
	@Column(name="content",nullable=true,jdbcType=JdbcType.VARCHAR,comment="内容")
	private String content;
	
	/**
	 * 路径
	 */
	@Column(name="path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="路径")
	private String path;
	
	/**
	 * createtime
	 */
	@Column(name="createtime",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="createtime")
	private Date createtime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}