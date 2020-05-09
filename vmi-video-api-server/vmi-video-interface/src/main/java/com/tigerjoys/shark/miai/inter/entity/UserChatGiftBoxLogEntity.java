package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户礼物盒流水[t_user_chat_gift_box_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-30 15:13:42
 *
 */
@Table(name="t_user_chat_gift_box_log")
public class UserChatGiftBoxLogEntity extends BaseEntity implements Serializable {

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
	 * 对方ID
	 */
	@Column(name="other_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="对方ID")
	private Long other_id;
	
	/**
	 * 礼物ID
	 */
	@Column(name="gift_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="礼物ID")
	private Long gift_id;
	
	/**
	 * 剩余数量
	 */
	@Column(name="balance",nullable=true,jdbcType=JdbcType.BIGINT,comment="剩余数量")
	private Long balance;
	
	/**
	 * 收入或者消费的数量
	 */
	@Column(name="amount",nullable=false,jdbcType=JdbcType.BIGINT,comment="收入或者消费的数量")
	private Long amount;
	
	/**
	 * 消费类型或充值类型
	 */
	@Column(name="log_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="消费类型或充值类型")
	private Integer log_type;
	
	/**
	 * 日志类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="日志类型")
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getOther_id() {
		return other_id;
	}

	public void setOther_id(Long other_id) {
		this.other_id = other_id;
	}
	
	public Long getGift_id() {
		return gift_id;
	}

	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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