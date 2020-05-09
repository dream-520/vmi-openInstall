package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  商品分组表[t_commodity_group] 表对应的实体类
 * @author lipeng
 * @Date 2018-12-07 15:20:02
 *
 */
@Table(name="t_commodity_group")
public class CommodityGroupEntity extends BaseEntity implements Serializable {

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
	 * 低值
	 */
	@Column(name="low_value",nullable=false,jdbcType=JdbcType.INTEGER,comment="低值")
	private Integer low_value;
	
	/**
	 * 高值
	 */
	@Column(name="high_value",nullable=false,jdbcType=JdbcType.INTEGER,comment="高值")
	private Integer high_value;
	
	/**
	 * 分组标题
	 */
	@Column(name="group_detail",nullable=false,jdbcType=JdbcType.VARCHAR,comment="分组标题")
	private String group_detail;
	
	/**
	 * 类型 0购买钻石 1签到
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型 0购买钻石 1签到")
	private Integer type;
	
	/**
	 * 状态1正常 0下架 -9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态1正常 0下架 -9删除")
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
	
	public Integer getLow_value() {
		return low_value;
	}

	public void setLow_value(Integer low_value) {
		this.low_value = low_value;
	}
	
	public Integer getHigh_value() {
		return high_value;
	}

	public void setHigh_value(Integer high_value) {
		this.high_value = high_value;
	}
	
	public String getGroup_detail() {
		return group_detail;
	}

	public void setGroup_detail(String group_detail) {
		this.group_detail = group_detail;
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