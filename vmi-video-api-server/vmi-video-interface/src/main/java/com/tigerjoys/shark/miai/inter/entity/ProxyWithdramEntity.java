package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  代理人提现管理[t_proxy_withdram] 表对应的实体类
 * @author yangjunming
 * @Date 2017-11-06 17:27:45
 *
 */
@Table(name="t_proxy_withdram")
public class ProxyWithdramEntity extends BaseEntity implements Serializable {

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
	 * 提现流水号
	 */
	@Column(name="transaction_flow",nullable=true,jdbcType=JdbcType.VARCHAR,comment="提现流水号")
	private String transaction_flow;
	
	/**
	 * 支付类型 1 支付宝 2 微信 3 银行卡
	 */
	@Column(name="pay_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="支付类型 1 支付宝 2 微信 3 银行卡")
	private Integer pay_type;
	
	/**
	 * 支付账号
	 */
	@Column(name="pay_account",nullable=true,jdbcType=JdbcType.VARCHAR,comment="支付账号")
	private String pay_account;
	
	/**
	 * 支付名称
	 */
	@Column(name="pay_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="支付名称")
	private String pay_name;
	
	/**
	 * 提现金额
	 */
	@Column(name="amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="提现金额")
	private Integer amount;
	
	/**
	 * 提现状态
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="提现状态")
	private Integer status;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
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
	
	public String getTransaction_flow() {
		return transaction_flow;
	}

	public void setTransaction_flow(String transaction_flow) {
		this.transaction_flow = transaction_flow;
	}
	
	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	
	public String getPay_account() {
		return pay_account;
	}

	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	
	public String getPay_name() {
		return pay_name;
	}

	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
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
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}