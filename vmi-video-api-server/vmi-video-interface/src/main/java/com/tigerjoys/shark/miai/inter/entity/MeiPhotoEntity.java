package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_mei_photo] 表对应的实体类
 * @author chengang
 * @Date 2017-12-28 10:47:40
 *
 */
@Table(name="t_mei_photo")
public class MeiPhotoEntity extends BaseEntity implements Serializable {

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
	 * uid
	 */
	@Column(name="uid",nullable=false,jdbcType=JdbcType.BIGINT,comment="uid")
	private Long uid;
	
	/**
	 * path
	 */
	@Column(name="path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="path")
	private String path;
	
	/**
	 * extend
	 */
	@Column(name="extend",nullable=true,jdbcType=JdbcType.VARCHAR,comment="extend")
	private String extend;
	
	/**
	 * local_path
	 */
	@Column(name="local_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="local_path")
	private String local_path;
	
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
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}
	
	public String getLocal_path() {
		return local_path;
	}

	public void setLocal_path(String local_path) {
		this.local_path = local_path;
	}
	
}