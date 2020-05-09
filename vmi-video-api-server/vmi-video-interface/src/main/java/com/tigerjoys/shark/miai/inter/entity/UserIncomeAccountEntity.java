package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户收益账户[t_user_income_account] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Table(name="t_user_income_account")
public class UserIncomeAccountEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 总收益
	 */
	@Column(name="deposit",nullable=false,jdbcType=JdbcType.BIGINT,comment="总收益")
	private Long deposit;
	
	/**
	 * 提取收益
	 */
	@Column(name="extract",nullable=false,jdbcType=JdbcType.BIGINT,comment="提取收益")
	private Long extract;
	
	/**
	 * 剩余收益
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="剩余收益")
	private Long balance;
	
	/**
	 * 1正常，-1冻结
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常，-1冻结")
	private Integer status;
	
	/**
	 * 收益类型；1现金，2票
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="收益类型；1现金，2票")
	private Integer type;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	
	public Long getExtract() {
		return extract;
	}

	public void setExtract(Long extract) {
		this.extract = extract;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}