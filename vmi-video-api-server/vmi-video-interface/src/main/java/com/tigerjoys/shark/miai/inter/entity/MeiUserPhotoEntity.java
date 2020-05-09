package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_mei_user_photo] 表对应的实体类
 * @author chengang
 * @Date 2017-12-28 11:19:11
 *
 */
@Table(name="t_mei_user_photo")
public class MeiUserPhotoEntity extends BaseEntity implements Serializable {

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
	 * 美丽约id
	 */
	@Column(name="uid",nullable=false,jdbcType=JdbcType.BIGINT,comment="美丽约id")
	private Long uid;
	
	/**
	 * 图片路径
	 */
	@Column(name="path",nullable=false,jdbcType=JdbcType.VARCHAR,comment="图片路径")
	private String path;
	
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
	
}