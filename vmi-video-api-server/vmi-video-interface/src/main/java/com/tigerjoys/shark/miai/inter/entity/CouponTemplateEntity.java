package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  代金券类别[t_coupon_template] 表对应的实体类
 * @author yangjunming
 * @Date 2017-09-08 15:30:38
 *
 */
@Table(name="t_coupon_template")
public class CouponTemplateEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 代金券类别ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="代金券类别ID")
	private Long id;
	
	/**
	 * 代金券名称
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="代金券名称")
	private String name;
	
	/**
	 * 代金券金额
	 */
	@Column(name="amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="代金券金额")
	private Integer amount;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 有效期多少天
	 */
	@Column(name="validity",nullable=false,jdbcType=JdbcType.INTEGER,comment="有效期多少天")
	private Integer validity;
	
	/**
	 * 代金券来源组，:0-新会员，1-充值送，2 邀请送
	 */
	@Column(name="group_id",nullable=true,jdbcType=JdbcType.INTEGER,comment="代金券来源组，:0-新会员，1-充值送，2 邀请送")
	private Integer group_id;
	
	/**
	 * 代金券类型 0 付费约
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.INTEGER,comment="代金券类型 0 付费约")
	private Integer type;
	
	/**
	 * 代金券状态；0-下架,1-上架
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="代金券状态；0-下架,1-上架")
	private Integer status;
	
	/**
	 * 代金券可使用的金额
	 */
	@Column(name="use_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="代金券可使用的金额")
	private Integer use_amount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	
	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
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
	
	public Integer getUse_amount() {
		return use_amount;
	}

	public void setUse_amount(Integer use_amount) {
		this.use_amount = use_amount;
	}
	
}