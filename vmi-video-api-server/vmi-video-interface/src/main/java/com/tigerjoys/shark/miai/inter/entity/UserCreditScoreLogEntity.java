package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户信用分账户流水[t_user_credit_score_log] 表对应的实体类
 * @author liuman
 * @Date 2017-08-18 19:52:17
 *
 */
@Table(name="t_user_credit_score_log")
public class UserCreditScoreLogEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 信用分账户ID
	 */
	@Column(name="accountid",nullable=false,jdbcType=JdbcType.BIGINT,comment="信用分账户ID")
	private Long accountid;
	
	/**
	 * 增加或减少的信用分
	 */
	@Column(name="amount",nullable=false,jdbcType=JdbcType.BIGINT,comment="增加或减少的信用分")
	private Long amount;
	
	/**
	 * 当前信用分余额
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="当前信用分余额")
	private Long balance;
	
	/**
	 * 0消费，1获取
	 */
	@Column(name="logtype",nullable=false,jdbcType=JdbcType.TINYINT,comment="0消费，1获取")
	private Integer logtype;
	
	/**
	 * 0未知,其他参考枚举类
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="0未知,其他参考枚举类")
	private Integer type;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
	/**
	 * 交易流水标识
	 */
	@Column(name="transaction_flow",nullable=false,jdbcType=JdbcType.VARCHAR,comment="交易流水标识")
	private String transaction_flow;
	
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
	
	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
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
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getTransaction_flow() {
		return transaction_flow;
	}

	public void setTransaction_flow(String transaction_flow) {
		this.transaction_flow = transaction_flow;
	}
	
}