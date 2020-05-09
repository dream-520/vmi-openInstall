package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  提现申请管理表[t_user_income_withdrawal] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-01-25 14:59:50
 *
 */
@Table(name="t_user_income_withdrawal")
public class UserIncomeWithdrawalEntity extends BaseEntity implements Serializable {

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
	 * 序列号
	 */
	@Column(name="serial",nullable=false,jdbcType=JdbcType.BIGINT,comment="序列号")
	private Long serial;
	
	/**
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 申请金额(分)
	 */
	@Column(name="apply_money",nullable=false,jdbcType=JdbcType.INTEGER,comment="申请金额(分)")
	private Integer apply_money;
	
	/**
	 * 劳务扣税
	 */
	@Column(name="tax",nullable=false,jdbcType=JdbcType.INTEGER,comment="劳务扣税")
	private Integer tax;
	
	/**
	 * 奖励金(分)
	 */
	@Column(name="bonus",nullable=false,jdbcType=JdbcType.INTEGER,comment="奖励金(分)")
	private Integer bonus;
	
	/**
	 * 奖励金扣税
	 */
	@Column(name="bonux_tax",nullable=false,jdbcType=JdbcType.INTEGER,comment="奖励金扣税")
	private Integer bonux_tax;
	
	/**
	 * 分成配置（json）
	 */
	@Column(name="vacuate",nullable=false,jdbcType=JdbcType.VARCHAR,comment="分成配置（json）")
	private String vacuate;
	
	/**
	 * 收益类型；1现金(服务)，2票(礼物)
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="收益类型；1现金(服务)，2票(礼物)")
	private Integer type;
	
	/**
	 * 提现方式 1.余额 2.支付宝
	 */
	@Column(name="via",nullable=false,jdbcType=JdbcType.TINYINT,comment="提现方式 1.余额 2.支付宝")
	private Integer via;
	
	/**
	 * 状态 0.审核中 1.结算 2.拒绝
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态 0.审核中 1.结算 2.拒绝")
	private Integer status;
	
	/**
	 * 操作人ID
	 */
	@Column(name="update_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="操作人ID")
	private Long update_adminid;
	
	/**
	 * 用户姓名
	 */
	@Column(name="ali_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户姓名")
	private String ali_name;
	
	/**
	 * 支付宝账号
	 */
	@Column(name="ali_account",nullable=true,jdbcType=JdbcType.VARCHAR,comment="支付宝账号")
	private String ali_account;
	
	/**
	 * 转账支付宝订单号
	 */
	@Column(name="ali_trade_no",nullable=true,jdbcType=JdbcType.VARCHAR,comment="转账支付宝订单号")
	private String ali_trade_no;
	
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
	
	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getApply_money() {
		return apply_money;
	}

	public void setApply_money(Integer apply_money) {
		this.apply_money = apply_money;
	}
	
	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}
	
	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	
	public Integer getBonux_tax() {
		return bonux_tax;
	}

	public void setBonux_tax(Integer bonux_tax) {
		this.bonux_tax = bonux_tax;
	}
	
	public String getVacuate() {
		return vacuate;
	}

	public void setVacuate(String vacuate) {
		this.vacuate = vacuate;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getVia() {
		return via;
	}

	public void setVia(Integer via) {
		this.via = via;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getUpdate_adminid() {
		return update_adminid;
	}

	public void setUpdate_adminid(Long update_adminid) {
		this.update_adminid = update_adminid;
	}
	
	public String getAli_name() {
		return ali_name;
	}

	public void setAli_name(String ali_name) {
		this.ali_name = ali_name;
	}
	
	public String getAli_account() {
		return ali_account;
	}

	public void setAli_account(String ali_account) {
		this.ali_account = ali_account;
	}
	
	public String getAli_trade_no() {
		return ali_trade_no;
	}

	public void setAli_trade_no(String ali_trade_no) {
		this.ali_trade_no = ali_trade_no;
	}
	
}