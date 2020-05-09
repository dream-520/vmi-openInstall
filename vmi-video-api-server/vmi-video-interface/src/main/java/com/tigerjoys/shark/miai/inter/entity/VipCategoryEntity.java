package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  VIP类型[t_vip_category] 表对应的实体类
 * @author yangjunming
 * @Date 2018-08-22 17:52:03
 *
 */
@Table(name="t_vip_category")
public class VipCategoryEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * VIP类别ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="VIP类别ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
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
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.BIGINT,comment="钻石")
	private Long diamond;
	
	/**
	 * 能量
	 */
	@Column(name="energy",nullable=true,jdbcType=JdbcType.BIGINT,comment="能量")
	private Long energy;
	
	/**
	 * 延长月数
	 */
	@Column(name="months",nullable=false,jdbcType=JdbcType.INTEGER,comment="延长月数")
	private Integer months;
	
	/**
	 * 购买数量
	 */
	@Column(name="buy_num",nullable=false,jdbcType=JdbcType.BIGINT,comment="购买数量")
	private Long buy_num;
	
	/**
	 * 是否只能购买一次 0 多次购买 1 只能购买一次
	 */
	@Column(name="buy_single",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否只能购买一次 0 多次购买 1 只能购买一次")
	private Integer buy_single;
	
	/**
	 * 显示顺序
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="显示顺序")
	private Integer priority;
	
	/**
	 * 状态  0下架  1上架
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态  0下架  1上架")
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
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Long getEnergy() {
		return energy;
	}

	public void setEnergy(Long energy) {
		this.energy = energy;
	}
	
	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}
	
	public Long getBuy_num() {
		return buy_num;
	}

	public void setBuy_num(Long buy_num) {
		this.buy_num = buy_num;
	}
	
	public Integer getBuy_single() {
		return buy_single;
	}

	public void setBuy_single(Integer buy_single) {
		this.buy_single = buy_single;
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
	
}