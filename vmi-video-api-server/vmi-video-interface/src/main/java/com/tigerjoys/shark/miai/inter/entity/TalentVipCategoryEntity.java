package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  达人VIP字典表[t_talent_vip_category] 表对应的实体类
 * @author chengang
 * @Date 2017-08-21 08:55:32
 *
 */
@Table(name="t_talent_vip_category")
public class TalentVipCategoryEntity extends BaseEntity implements Serializable {

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
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="名称")
	private String name;
	
	/**
	 * 图片地址
	 */
	@Column(name="icon",nullable=true,jdbcType=JdbcType.VARCHAR,comment="图片地址")
	private String icon;
	
	/**
	 * 钻石
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="钻石")
	private Integer diamond;
	
	/**
	 * 延长月数
	 */
	@Column(name="months",nullable=false,jdbcType=JdbcType.INTEGER,comment="延长月数")
	private Integer months;
	
	/**
	 * 显示顺序
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="显示顺序")
	private Integer priority;
	
	/**
	 * 首否被选中
	 */
	@Column(name="checked",nullable=false,jdbcType=JdbcType.TINYINT,comment="首否被选中")
	private Integer checked;
	
	/**
	 * 状态,0下架,1上架
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态,0下架,1上架")
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	
}