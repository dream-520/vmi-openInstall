package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户付费查看用户信息记录[t_user_photo_checked_log] 表对应的实体类
 * @author chengang
 * @Date 2020-02-24 16:47:47
 *
 */
@Table(name="t_user_photo_checked_log")
public class UserPhotoCheckedLogEntity extends BaseEntity implements Serializable {

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
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 主播ID
	 */
	@Column(name="anchor_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播ID")
	private Long anchor_id;
	
	/**
	 * 作品ID
	 */
	@Column(name="photo_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="作品ID")
	private Long photo_id;
	
	/**
	 * 消费钻石数
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="消费钻石数")
	private Integer diamond;
	
	/**
	 * 类型：PhotoCheckedLogTypeEnum枚举
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型：PhotoCheckedLogTypeEnum枚举")
	private Integer type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getAnchor_id() {
		return anchor_id;
	}

	public void setAnchor_id(Long anchor_id) {
		this.anchor_id = anchor_id;
	}
	
	public Long getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(Long photo_id) {
		this.photo_id = photo_id;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}