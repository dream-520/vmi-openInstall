package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  动态资源表[t_user_photo_resource] 表对应的实体类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Table(name="t_user_photo_resource")
public class UserPhotoResourceEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资源ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="资源ID")
	private Long id;
	
	/**
	 * 所属用户
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="所属用户")
	private Long userid;
	
	/**
	 * 资源路径
	 */
	@Column(name="path",nullable=false,jdbcType=JdbcType.VARCHAR,comment="资源路径")
	private String path;
	
	/**
	 * 资源状态
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="资源状态")
	private Integer state;
	
	/**
	 * 是否对相册图片进行模糊 0 不模糊  1 模糊
	 */
	@Column(name="obscure",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否对相册图片进行模糊 0 不模糊  1 模糊")
	private Integer obscure;
	
	/**
	 * 相册模糊路径
	 */
	@Column(name="obscure_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="相册模糊路径")
	private String obscure_path;
	
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
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getObscure() {
		return obscure;
	}

	public void setObscure(Integer obscure) {
		this.obscure = obscure;
	}
	
	public String getObscure_path() {
		return obscure_path;
	}

	public void setObscure_path(String obscure_path) {
		this.obscure_path = obscure_path;
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
	
}