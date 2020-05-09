package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户阅读消息条数记录[t_message_user_read] 表对应的实体类
 * @author shiming
 * @Date 2019-07-09 17:25:45
 *
 */
@Table(name="t_message_user_read")
public class MessageUserReadEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 全部类型消息阅读条数
	 */
	@Column(name="all_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="全部类型消息阅读条数")
	private Integer all_num;
	
	/**
	 * 用户阅读消息条数
	 */
	@Column(name="user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户阅读消息条数")
	private Integer user_num;
	
	/**
	 * 主播阅读消息条数
	 */
	@Column(name="anchor_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播阅读消息条数")
	private Integer anchor_num;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
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
	
	public Integer getAll_num() {
		return all_num;
	}

	public void setAll_num(Integer all_num) {
		this.all_num = all_num;
	}
	
	public Integer getUser_num() {
		return user_num;
	}

	public void setUser_num(Integer user_num) {
		this.user_num = user_num;
	}
	
	public Integer getAnchor_num() {
		return anchor_num;
	}

	public void setAnchor_num(Integer anchor_num) {
		this.anchor_num = anchor_num;
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