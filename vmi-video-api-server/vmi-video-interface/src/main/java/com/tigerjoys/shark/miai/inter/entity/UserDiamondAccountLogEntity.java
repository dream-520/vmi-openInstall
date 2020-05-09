package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户钻石账户流水[t_user_diamond_account_log] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Table(name="t_user_diamond_account_log")
public class UserDiamondAccountLogEntity extends BaseEntity implements Serializable {

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
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 充值或者消费的钻石
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.BIGINT,comment="充值或者消费的钻石")
	private Long diamond;
	
	/**
	 * 账户剩余钻石
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="账户剩余钻石")
	private Long balance;
	
	/**
	 * 0消费，1收入
	 */
	@Column(name="log_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="0消费，1收入")
	private Integer log_type;
	
	/**
	 * 消费类型或充值类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="消费类型或充值类型")
	private Integer type;
	
	/**
	 * 支付类型
	 */
	@Column(name="pay_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="支付类型")
	private Integer pay_type;
	
	/**
	 * 充值对应的钱(分)
	 */
	@Column(name="money",nullable=true,jdbcType=JdbcType.BIGINT,comment="充值对应的钱(分)")
	private Long money;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Integer getLog_type() {
		return log_type;
	}

	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	
	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
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