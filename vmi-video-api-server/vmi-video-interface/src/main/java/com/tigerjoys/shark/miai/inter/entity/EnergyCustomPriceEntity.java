package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充能量自定义价格列表[t_energy_custom_price] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Table(name="t_energy_custom_price")
public class EnergyCustomPriceEntity extends BaseEntity implements Serializable {

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
	 * 分类ID
	 */
	@Column(name="category_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="分类ID")
	private Long category_id;
	
	/**
	 * 商品标识符
	 */
	@Column(name="price_identifier",nullable=true,jdbcType=JdbcType.VARCHAR,comment="商品标识符")
	private String price_identifier;
	
	/**
	 * 充值金额(分)
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值金额(分)")
	private Integer money;
	
	/**
	 * 充值金额对应能量数量
	 */
	@Column(name="energy",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值金额对应能量数量")
	private Integer energy;
	
	/**
	 * 赠送能量数量
	 */
	@Column(name="donor",nullable=false,jdbcType=JdbcType.INTEGER,comment="赠送能量数量")
	private Integer donor;
	
	/**
	 * 主标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="主标题")
	private String title;
	
	/**
	 * 描述
	 */
	@Column(name="description",nullable=false,jdbcType=JdbcType.VARCHAR,comment="描述")
	private String description;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="优先级")
	private Integer priority;
	
	/**
	 * 状态，1正常，0停用
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态，1正常，0停用")
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
	
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	public String getPrice_identifier() {
		return price_identifier;
	}

	public void setPrice_identifier(String price_identifier) {
		this.price_identifier = price_identifier;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	
	public Integer getDonor() {
		return donor;
	}

	public void setDonor(Integer donor) {
		this.donor = donor;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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