package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  现金流表[t_cash_flow] 表对应的实体类
 * @author chengang
 * @Date 2017-05-11 15:39:46
 *
 */
@Table(name="t_cash_flow")
public class CashFlowEntity extends BaseEntity implements Serializable {

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
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 订单ID,可能为提现ID或者充值ID
	 */
	@Column(name="order_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="订单ID,可能为提现ID或者充值ID")
	private Long order_id;
	
	/**
	 * 类型，参见现金流枚举
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="类型，参见现金流枚举")
	private Integer type;
	
	/**
	 * 交易流水标识
	 */
	@Column(name="transaction_id",nullable=false,jdbcType=JdbcType.VARCHAR,comment="交易流水标识")
	private String transaction_id;
	
	/**
	 * 现金
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.BIGINT,comment="现金")
	private Long money;
	
	/**
	 * 用户openid
	 */
	@Column(name="user_openid",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户openid")
	private String user_openid;
	
	/**
	 * 用户帐号,可以手机号码,email等都行
	 */
	@Column(name="user_account",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户帐号,可以手机号码,email等都行")
	private String user_account;
	
	/**
	 * 0支出，1收入
	 */
	@Column(name="logtype",nullable=false,jdbcType=JdbcType.TINYINT,comment="0支出，1收入")
	private Integer logtype;
	
	/**
	 * 1正常,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常,-9删除")
	private Integer status;
	
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
	
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
	
	public String getUser_openid() {
		return user_openid;
	}

	public void setUser_openid(String user_openid) {
		this.user_openid = user_openid;
	}
	
	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	
	public Integer getLogtype() {
		return logtype;
	}

	public void setLogtype(Integer logtype) {
		this.logtype = logtype;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}