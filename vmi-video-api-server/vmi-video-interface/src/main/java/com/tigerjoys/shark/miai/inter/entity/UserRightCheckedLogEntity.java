package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户付费查看用户信息记录[t_user_right_checked_log] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-06-05 16:37:45
 *
 */
@Table(name="t_user_right_checked_log")
public class UserRightCheckedLogEntity extends BaseEntity implements Serializable {

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
	 * 对方ID
	 */
	@Column(name="other_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="对方ID")
	private Long other_id;
	
	/**
	 * 消费钻石数
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="消费钻石数")
	private Integer diamond;
	
	/**
	 * 类型：1-手机，2-视频
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型：1-手机，2-视频")
	private Integer type;
	
	/**
	 * 状态：0-未付费，1-已付费
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态：0-未付费，1-已付费")
	private Integer status;
	
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
	
	public Long getOther_id() {
		return other_id;
	}

	public void setOther_id(Long other_id) {
		this.other_id = other_id;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}