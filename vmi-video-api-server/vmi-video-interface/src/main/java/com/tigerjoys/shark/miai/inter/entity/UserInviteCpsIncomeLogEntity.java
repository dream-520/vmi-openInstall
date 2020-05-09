package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_user_invite_cps_income_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-21 15:10:22
 *
 */
@Table(name="t_user_invite_cps_income_log")
public class UserInviteCpsIncomeLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 上1级用户
	 */
	@Column(name="parent1",nullable=true,jdbcType=JdbcType.BIGINT,comment="上1级用户")
	private Long parent1;
	
	/**
	 * 上2级用户
	 */
	@Column(name="parent2",nullable=true,jdbcType=JdbcType.BIGINT,comment="上2级用户")
	private Long parent2;
	
	/**
	 * 上3级用户
	 */
	@Column(name="parent3",nullable=true,jdbcType=JdbcType.BIGINT,comment="上3级用户")
	private Long parent3;
	
	/**
	 * 上1级用户收益
	 */
	@Column(name="parent1_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="上1级用户收益")
	private Long parent1_income;
	
	/**
	 * 上2级用户收益
	 */
	@Column(name="parent2_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="上2级用户收益")
	private Long parent2_income;
	
	/**
	 * 上3级用户收益
	 */
	@Column(name="parent3_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="上3级用户收益")
	private Long parent3_income;
	
	/**
	 * 收益
	 */
	@Column(name="amount",nullable=true,jdbcType=JdbcType.BIGINT,comment="收益")
	private Long amount;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
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
	
	public Long getParent1() {
		return parent1;
	}

	public void setParent1(Long parent1) {
		this.parent1 = parent1;
	}
	
	public Long getParent2() {
		return parent2;
	}

	public void setParent2(Long parent2) {
		this.parent2 = parent2;
	}
	
	public Long getParent3() {
		return parent3;
	}

	public void setParent3(Long parent3) {
		this.parent3 = parent3;
	}
	
	public Long getParent1_income() {
		return parent1_income;
	}

	public void setParent1_income(Long parent1_income) {
		this.parent1_income = parent1_income;
	}
	
	public Long getParent2_income() {
		return parent2_income;
	}

	public void setParent2_income(Long parent2_income) {
		this.parent2_income = parent2_income;
	}
	
	public Long getParent3_income() {
		return parent3_income;
	}

	public void setParent3_income(Long parent3_income) {
		this.parent3_income = parent3_income;
	}
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}