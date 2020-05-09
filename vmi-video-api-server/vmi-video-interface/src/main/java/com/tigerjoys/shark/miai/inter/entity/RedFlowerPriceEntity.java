package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  小红花价格列表[t_red_flower_price] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-06 18:41:09
 *
 */
@Table(name="t_red_flower_price")
public class RedFlowerPriceEntity extends BaseEntity implements Serializable {

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
	@Column(name="category_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="分类ID")
	private Long category_id;
	
	/**
	 * IOS的IPA价格标识
	 */
	@Column(name="price_identifier",nullable=true,jdbcType=JdbcType.VARCHAR,comment="IOS的IPA价格标识")
	private String price_identifier;
	
	/**
	 * 充值金额(分)
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值金额(分)")
	private Integer money;
	
	/**
	 * 充值金额对应小红花数量
	 */
	@Column(name="flower",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值金额对应小红花数量")
	private Integer flower;
	
	/**
	 * 0 送花 1 送钱
	 */
	@Column(name="donor_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 送花 1 送钱")
	private Integer donor_type;
	
	/**
	 * 0 首次,1 每次
	 */
	@Column(name="donor_times",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 首次,1 每次")
	private Integer donor_times;
	
	/**
	 * 赠送小红花数量
	 */
	@Column(name="donor",nullable=false,jdbcType=JdbcType.INTEGER,comment="赠送小红花数量")
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
	 * 复充标题 
	 */
	@Column(name="repetition_desc",nullable=true,jdbcType=JdbcType.VARCHAR,comment="复充标题 ")
	private String repetition_desc;
	
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
	
	public Integer getFlower() {
		return flower;
	}

	public void setFlower(Integer flower) {
		this.flower = flower;
	}
	
	public Integer getDonor_type() {
		return donor_type;
	}

	public void setDonor_type(Integer donor_type) {
		this.donor_type = donor_type;
	}
	
	public Integer getDonor_times() {
		return donor_times;
	}

	public void setDonor_times(Integer donor_times) {
		this.donor_times = donor_times;
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
	
	public String getRepetition_desc() {
		return repetition_desc;
	}

	public void setRepetition_desc(String repetition_desc) {
		this.repetition_desc = repetition_desc;
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