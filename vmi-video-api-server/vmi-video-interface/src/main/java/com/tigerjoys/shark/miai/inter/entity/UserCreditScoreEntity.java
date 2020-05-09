package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户信用分账户[t_user_credit_score] 表对应的实体类
 * @author liuman
 * @Date 2017-08-14 18:43:02
 *
 */
@Table(name="t_user_credit_score")
public class UserCreditScoreEntity extends BaseEntity implements Serializable {

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
	 * 更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 获取账户信用分总额
	 */
	@Column(name="deposit",nullable=false,jdbcType=JdbcType.BIGINT,comment="获取账户信用分总额")
	private Long deposit;
	
	/**
	 * 账户信用分扣除总额
	 */
	@Column(name="consume",nullable=false,jdbcType=JdbcType.BIGINT,comment="账户信用分扣除总额")
	private Long consume;
	
	/**
	 * 账户信用分余额
	 */
	@Column(name="balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="账户信用分余额")
	private Long balance;
	
	/**
	 * 1正常，-1冻结
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常，-1冻结")
	private Integer status;
	
	/**
	 * 类型:1-普通，2-服务者
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型:1-普通，2-服务者")
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	
	public Long getConsume() {
		return consume;
	}

	public void setConsume(Long consume) {
		this.consume = consume;
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