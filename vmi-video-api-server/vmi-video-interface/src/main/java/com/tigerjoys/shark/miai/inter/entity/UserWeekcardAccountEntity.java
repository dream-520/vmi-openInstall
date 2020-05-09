package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户周卡账户表[t_user_weekcard_account] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-14 14:56:08
 *
 */
@Table(name="t_user_weekcard_account")
public class UserWeekcardAccountEntity extends BaseEntity implements Serializable {

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
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 周卡ID
	 */
	@Column(name="card_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="周卡ID")
	private Long card_id;
	
	/**
	 * 类型 4绿钻卡 5黄钻卡 
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型 4绿钻卡 5黄钻卡 ")
	private Integer type;
	
	/**
	 * 开始时间
	 */
	@Column(name="start_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="开始时间")
	private Date start_time;
	
	/**
	 * 结束时间
	 */
	@Column(name="end_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="结束时间")
	private Date end_time;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getCard_id() {
		return card_id;
	}

	public void setCard_id(Long card_id) {
		this.card_id = card_id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
}