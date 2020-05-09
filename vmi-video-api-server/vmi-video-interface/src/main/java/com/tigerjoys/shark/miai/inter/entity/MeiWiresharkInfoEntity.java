package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_mei_wireshark_info] 表对应的实体类
 * @author shiming
 * @Date 2018-06-01 15:10:53
 *
 */
@Table(name="t_mei_wireshark_info")
public class MeiWiresharkInfoEntity extends BaseEntity implements Serializable {

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
	 * 头像
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="头像")
	private String photo;
	
	/**
	 * 相册
	 */
	@Column(name="photos",nullable=true,jdbcType=JdbcType.VARCHAR,comment="相册")
	private String photos;
	
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
}