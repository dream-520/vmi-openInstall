package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充钻自定义分类表[t_recharge_custom_category] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-06 20:21:53
 *
 */
@Table(name="t_recharge_custom_category")
public class RechargeCustomCategoryEntity extends BaseEntity implements Serializable {

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
	 * 手机价格区间名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机价格区间名称")
	private String name;
	
	/**
	 * 一元首冲，1开，0关
	 */
	@Column(name="first",nullable=false,jdbcType=JdbcType.TINYINT,comment="一元首冲，1开，0关")
	private Integer first;
	
	/**
	 * 首充标题
	 */
	@Column(name="first_title",nullable=true,jdbcType=JdbcType.VARCHAR,comment="首充标题")
	private String first_title;
	
	/**
	 * 首充描述
	 */
	@Column(name="first_disc",nullable=true,jdbcType=JdbcType.VARCHAR,comment="首充描述")
	private String first_disc;
	
	/**
	 * 首充金额
	 */
	@Column(name="first_money",nullable=true,jdbcType=JdbcType.DECIMAL,comment="首充金额")
	private Double first_money;
	
	/**
	 * 首充钻石
	 */
	@Column(name="first_diamond",nullable=true,jdbcType=JdbcType.INTEGER,comment="首充钻石")
	private Integer first_diamond;
	
	/**
	 * 小红花首充状态
	 */
	@Column(name="first_flower_status",nullable=true,jdbcType=JdbcType.TINYINT,comment="小红花首充状态")
	private Integer first_flower_status;
	
	/**
	 * 小红花首充票题
	 */
	@Column(name="first_flower_title",nullable=true,jdbcType=JdbcType.VARCHAR,comment="小红花首充票题")
	private String first_flower_title;
	
	/**
	 * 小红花首充描述
	 */
	@Column(name="first_flower_disc",nullable=true,jdbcType=JdbcType.VARCHAR,comment="小红花首充描述")
	private String first_flower_disc;
	
	/**
	 * 小红花首充价格
	 */
	@Column(name="first_flower_money",nullable=true,jdbcType=JdbcType.DECIMAL,comment="小红花首充价格")
	private Double first_flower_money;
	
	/**
	 * 小红花首充数量
	 */
	@Column(name="first_flower_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="小红花首充数量")
	private Integer first_flower_num;
	
	/**
	 * 手机价格限制
	 */
	@Column(name="price",nullable=true,jdbcType=JdbcType.INTEGER,comment="手机价格限制")
	private Integer price;
	
	/**
	 * 状态，1正常，0停用，-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态，1正常，0停用，-9删除")
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
	
	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}
	
	public String getFirst_title() {
		return first_title;
	}

	public void setFirst_title(String first_title) {
		this.first_title = first_title;
	}
	
	public String getFirst_disc() {
		return first_disc;
	}

	public void setFirst_disc(String first_disc) {
		this.first_disc = first_disc;
	}
	
	public Double getFirst_money() {
		return first_money;
	}

	public void setFirst_money(Double first_money) {
		this.first_money = first_money;
	}
	
	public Integer getFirst_diamond() {
		return first_diamond;
	}

	public void setFirst_diamond(Integer first_diamond) {
		this.first_diamond = first_diamond;
	}
	
	public Integer getFirst_flower_status() {
		return first_flower_status;
	}

	public void setFirst_flower_status(Integer first_flower_status) {
		this.first_flower_status = first_flower_status;
	}
	
	public String getFirst_flower_title() {
		return first_flower_title;
	}

	public void setFirst_flower_title(String first_flower_title) {
		this.first_flower_title = first_flower_title;
	}
	
	public String getFirst_flower_disc() {
		return first_flower_disc;
	}

	public void setFirst_flower_disc(String first_flower_disc) {
		this.first_flower_disc = first_flower_disc;
	}
	
	public Double getFirst_flower_money() {
		return first_flower_money;
	}

	public void setFirst_flower_money(Double first_flower_money) {
		this.first_flower_money = first_flower_money;
	}
	
	public Integer getFirst_flower_num() {
		return first_flower_num;
	}

	public void setFirst_flower_num(Integer first_flower_num) {
		this.first_flower_num = first_flower_num;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}