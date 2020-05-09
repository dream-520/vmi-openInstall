package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户积分账户流水[t_user_point_account_log] 表对应的实体类
 * @author lipeng
 * @Date 2019-09-05 11:35:37
 *
 */
@Table(name="t_user_point_account_log")
public class UserPointAccountLogEntity extends BaseEntity implements Serializable {

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
	 * 充值或者消费的积分
	 */
	@Column(name="point",nullable=false,jdbcType=JdbcType.BIGINT,comment="充值或者消费的积分")
	private Long point;
	
	/**
	 * 账户剩余积分
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="账户剩余积分")
	private Long balance;
	
	/**
	 * 0消费，1收入
	 */
	@Column(name="log_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="0消费，1收入")
	private Integer log_type;
	
	/**
	 * 消费类型或收入类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="消费类型或收入类型")
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
	
	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
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