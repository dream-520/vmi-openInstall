package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  小红花订单表[t_red_flower_order] 表对应的实体类
 * @author yangjunming
 * @Date 2019-08-17 20:07:58
 *
 */
@Table(name="t_red_flower_order")
public class RedFlowerOrderEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单ID
	 */
	@Id(increment=false)
	@Column(name="order_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="订单ID")
	private Long order_id;
	
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
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 手机
	 */
	@Column(name="mobile",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机")
	private String mobile;
	
	/**
	 * 小红花商品ID
	 */
	@Column(name="price_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="小红花商品ID")
	private Long price_id;
	
	/**
	 * 小红花商品价格(分)
	 */
	@Column(name="price",nullable=false,jdbcType=JdbcType.INTEGER,comment="小红花商品价格(分)")
	private Integer price;
	
	/**
	 * 小红花数量（100份为1朵）
	 */
	@Column(name="flower",nullable=false,jdbcType=JdbcType.INTEGER,comment="小红花数量（100份为1朵）")
	private Integer flower;
	
	/**
	 * 0 送花  1 送钱
	 */
	@Column(name="donor_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 送花  1 送钱")
	private Integer donor_type;
	
	/**
	 * 0 首次,1 每次
	 */
	@Column(name="donor_times",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 首次,1 每次")
	private Integer donor_times;
	
	/**
	 * 赠送数量（100份为1朵）
	 */
	@Column(name="donor",nullable=false,jdbcType=JdbcType.INTEGER,comment="赠送数量（100份为1朵）")
	private Integer donor;
	
	/**
	 * 订单总额(分)
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="订单总额(分)")
	private Integer money;
	
	/**
	 * 花费收益额
	 */
	@Column(name="income",nullable=false,jdbcType=JdbcType.INTEGER,comment="花费收益额")
	private Integer income;
	
	/**
	 * 商品数量（100份为1朵）
	 */
	@Column(name="amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="商品数量（100份为1朵）")
	private Integer amount;
	
	/**
	 * 订单状态:0，未完成；1，已完成
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="订单状态:0，未完成；1，已完成")
	private Integer status;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Long getPrice_id() {
		return price_id;
	}

	public void setPrice_id(Long price_id) {
		this.price_id = price_id;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
	@Override
	public Long getId() {
		return order_id;
	}
}