package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户商品关系表[t_commodity_group_relationship] 表对应的实体类
 * @author lipeng
 * @Date 2018-12-10 14:35:58
 *
 */
@Table(name="t_commodity_group_relationship")
public class CommodityGroupRelationshipEntity extends BaseEntity implements Serializable {

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
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 分组ID
	 */
	@Column(name="group_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="分组ID")
	private Long group_id;
	
	/**
	 * 分组标题
	 */
	@Column(name="group_detail",nullable=false,jdbcType=JdbcType.VARCHAR,comment="分组标题")
	private String group_detail;
	
	/**
	 * 显示状态 1显示 0未显
	 */
	@Column(name="show_status",nullable=false,jdbcType=JdbcType.TINYINT,comment="显示状态 1显示 0未显")
	private Integer show_status;
	
	/**
	 * 状态1未领 0已领-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态1未领 0已领-9删除")
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}
	
	public String getGroup_detail() {
		return group_detail;
	}

	public void setGroup_detail(String group_detail) {
		this.group_detail = group_detail;
	}
	
	public Integer getShow_status() {
		return show_status;
	}

	public void setShow_status(Integer show_status) {
		this.show_status = show_status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}