package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  动态表[t_user_dynamic] 表对应的实体类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Table(name="t_user_dynamic")
public class UserDynamicEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID唯一标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID唯一标示")
	private Long id;
	
	/**
	 * 动态所有者id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="动态所有者id")
	private Long userid;
	
	/**
	 * 浏览人数(暂时没有排重处理)
	 */
	@Column(name="audience_num",nullable=false,jdbcType=JdbcType.BIGINT,comment="浏览人数(暂时没有排重处理)")
	private Long audience_num;
	
	/**
	 * 点赞数量(本字段暂时预存方便后期进行扩展)
	 */
	@Column(name="favor_num",nullable=false,jdbcType=JdbcType.BIGINT,comment="点赞数量(本字段暂时预存方便后期进行扩展)")
	private Long favor_num;
	
	/**
	 * 动态类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="动态类型")
	private Integer type;
	
	/**
	 * 动态状态 1正常 -9删除 0下架
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="动态状态 1正常 -9删除 0下架")
	private Integer state;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
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
	
	public Long getAudience_num() {
		return audience_num;
	}

	public void setAudience_num(Long audience_num) {
		this.audience_num = audience_num;
	}
	
	public Long getFavor_num() {
		return favor_num;
	}

	public void setFavor_num(Long favor_num) {
		this.favor_num = favor_num;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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