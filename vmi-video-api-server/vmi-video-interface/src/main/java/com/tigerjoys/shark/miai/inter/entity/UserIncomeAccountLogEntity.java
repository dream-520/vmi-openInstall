package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户收益流水[t_user_income_account_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-20 16:08:52
 *
 */
@Table(name="t_user_income_account_log")
public class UserIncomeAccountLogEntity extends BaseEntity implements Serializable {

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
	 * 初始收益额
	 */
	@Column(name="amount",nullable=false,jdbcType=JdbcType.BIGINT,comment="初始收益额")
	private Long amount;
	
	/**
	 * 内部收益额 
	 */
	@Column(name="internal_amount",nullable=true,jdbcType=JdbcType.BIGINT,comment="内部收益额 ")
	private Long internal_amount;
	
	/**
	 * 服务用户ID
	 */
	@Column(name="service_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="服务用户ID")
	private Long service_id;
	
	/**
	 * 工会ID 
	 */
	@Column(name="labor_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="工会ID ")
	private Long labor_id;
	
	/**
	 * 服务用户收益额
	 */
	@Column(name="service_amount",nullable=false,jdbcType=JdbcType.BIGINT,comment="服务用户收益额")
	private Long service_amount;
	
	/**
	 * 服务用户剩余收益额
	 */
	@Column(name="service_balance",nullable=false,jdbcType=JdbcType.BIGINT,comment="服务用户剩余收益额")
	private Long service_balance;
	
	/**
	 * 邀请者ID
	 */
	@Column(name="inviter_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="邀请者ID")
	private Long inviter_id;
	
	/**
	 * 邀请者收益额
	 */
	@Column(name="inviter_amount",nullable=true,jdbcType=JdbcType.BIGINT,comment="邀请者收益额")
	private Long inviter_amount;
	
	/**
	 * 邀请者剩余收益额
	 */
	@Column(name="inviter_balance",nullable=true,jdbcType=JdbcType.BIGINT,comment="邀请者剩余收益额")
	private Long inviter_balance;
	
	/**
	 * 平台收益额
	 */
	@Column(name="platform_amount",nullable=true,jdbcType=JdbcType.BIGINT,comment="平台收益额")
	private Long platform_amount;
	
	/**
	 * 0提取，1收入
	 */
	@Column(name="io",nullable=false,jdbcType=JdbcType.TINYINT,comment="0提取，1收入")
	private Integer io;
	
	/**
	 * 收益类型：1现金(服务收益或现金提取)，2票(礼物)，3现金（小费）
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="收益类型：1现金(服务收益或现金提取)，2票(礼物)，3现金（小费）")
	private Integer type;
	
	/**
	 * 交易流水标识
	 */
	@Column(name="transaction_flow",nullable=false,jdbcType=JdbcType.VARCHAR,comment="交易流水标识")
	private String transaction_flow;
	
	/**
	 * 分成配置（json）
	 */
	@Column(name="vacuate",nullable=false,jdbcType=JdbcType.VARCHAR,comment="分成配置（json）")
	private String vacuate;
	
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
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getInternal_amount() {
		return internal_amount;
	}

	public void setInternal_amount(Long internal_amount) {
		this.internal_amount = internal_amount;
	}
	
	public Long getService_id() {
		return service_id;
	}

	public void setService_id(Long service_id) {
		this.service_id = service_id;
	}
	
	public Long getLabor_id() {
		return labor_id;
	}

	public void setLabor_id(Long labor_id) {
		this.labor_id = labor_id;
	}
	
	public Long getService_amount() {
		return service_amount;
	}

	public void setService_amount(Long service_amount) {
		this.service_amount = service_amount;
	}
	
	public Long getService_balance() {
		return service_balance;
	}

	public void setService_balance(Long service_balance) {
		this.service_balance = service_balance;
	}
	
	public Long getInviter_id() {
		return inviter_id;
	}

	public void setInviter_id(Long inviter_id) {
		this.inviter_id = inviter_id;
	}
	
	public Long getInviter_amount() {
		return inviter_amount;
	}

	public void setInviter_amount(Long inviter_amount) {
		this.inviter_amount = inviter_amount;
	}
	
	public Long getInviter_balance() {
		return inviter_balance;
	}

	public void setInviter_balance(Long inviter_balance) {
		this.inviter_balance = inviter_balance;
	}
	
	public Long getPlatform_amount() {
		return platform_amount;
	}

	public void setPlatform_amount(Long platform_amount) {
		this.platform_amount = platform_amount;
	}
	
	public Integer getIo() {
		return io;
	}

	public void setIo(Integer io) {
		this.io = io;
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
	
	public String getVacuate() {
		return vacuate;
	}

	public void setVacuate(String vacuate) {
		this.vacuate = vacuate;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}