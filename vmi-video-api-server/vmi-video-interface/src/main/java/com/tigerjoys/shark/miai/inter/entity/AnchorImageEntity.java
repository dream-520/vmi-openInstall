package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播个人主页形象图片[t_anchor_image] 表对应的实体类
 * @author shiming
 * @Date 2019-07-03 19:24:27
 *
 */
@Table(name="t_anchor_image")
public class AnchorImageEntity extends BaseEntity implements Serializable {

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
	 * 资源状态 0 正常  -9 删除
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="资源状态 0 正常  -9 删除")
	private Integer state;
	
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