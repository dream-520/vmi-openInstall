package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_coupon] 表对应的实体类
 * @author yangjunming
 * @Date 2017-09-08 16:36:58
 *
 */
@Table(name="t_coupon")
public class CouponEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 代金券ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="代金券ID")
	private Long id;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 代金券名称
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="代金券名称")
	private String name;
	
	/**
	 * 代金券模板ID
	 */
	@Column(name="template_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="代金券模板ID")
	private Long template_id;
	
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
	 * 有效期开始时间
	 */
	@Column(name="start_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="有效期开始时间")
	private Date start_time;
	
	/**
	 * 有效期结束时间
	 */
	@Column(name="end_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="有效期结束时间")
	private Date end_time;
	
	/**
	 * 订单号
	 */
	@Column(name="orderid",nullable=true,jdbcType=JdbcType.BIGINT,comment="订单号")
	private Long orderid;
	
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
	 * 代金券状态；0-未使用,1-已使用,-9 删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="代金券状态；0-未使用,1-已使用,-9 删除")
	private Integer status;
	
	/**
	 * 代金券限额
	 */
	@Column(name="use_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="代金券限额")
	private Integer use_amount;
	
	/**
	 * 使用时间
	 */
	@Column(name="use_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="使用时间")
	private Date use_time;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(Long template_id) {
		this.template_id = template_id;
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
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
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
	
	public Date getUse_time() {
		return use_time;
	}

	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	
}