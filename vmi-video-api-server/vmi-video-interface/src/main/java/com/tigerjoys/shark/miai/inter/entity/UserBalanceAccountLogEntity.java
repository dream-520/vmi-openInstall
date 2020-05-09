package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户余额账户流水[t_user_balance_account_log] 表对应的实体类
 * @author chengang
 * @Date 2017-08-10 11:02:06
 *
 */
@Table(name="t_user_balance_account_log")
public class UserBalanceAccountLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.INTEGER,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 变更金额,单位(分)
	 */
	@Column(name="amount",nullable=false,jdbcType=JdbcType.BIGINT,comment="变更金额,单位(分)")
	private Long amount;
	
	/**
	 * 剩余金额,单位(分)
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="剩余金额,单位(分)")
	private Long balance;
	
	/**
	 * 0支出，1收入
	 */
	@Column(name="logtype",nullable=false,jdbcType=JdbcType.TINYINT,comment="0支出，1收入")
	private Integer logtype;
	
	/**
	 * 支出类型或收入类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="支出类型或收入类型")
	private Integer type;
	
	/**
	 * 交易流水标识
	 */
	@Column(name="transaction_flow",nullable=false,jdbcType=JdbcType.VARCHAR,comment="交易流水标识")
	private String transaction_flow;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Integer getLogtype() {
		return logtype;
	}

	public void setLogtype(Integer logtype) {
		this.logtype = logtype;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTransaction_flow() {
		return transaction_flow;
	}

	public void setTransaction_flow(String transaction_flow) {
		this.transaction_flow = transaction_flow;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}