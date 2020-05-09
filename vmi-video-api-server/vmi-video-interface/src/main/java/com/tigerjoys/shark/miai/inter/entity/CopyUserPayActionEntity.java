package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户支付记录[t_copy_user_pay_action] 表对应的实体类
 * @author lipeng
 * @Date 2019-12-17 20:19:28
 *
 */
@Table(name="t_copy_user_pay_action")
public class CopyUserPayActionEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id(increment=false)
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
	 * 手机
	 */
	@Column(name="mobile",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机")
	private String mobile;
	
	/**
	 * 系统类型，1安卓，2苹果
	 */
	@Column(name="app_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="系统类型，1安卓，2苹果")
	private Integer app_type;
	
	/**
	 * 系统渠道名称
	 */
	@Column(name="app_channel",nullable=false,jdbcType=JdbcType.VARCHAR,comment="系统渠道名称")
	private String app_channel;
	
	/**
	 * 应用版本号
	 */
	@Column(name="app_version",nullable=false,jdbcType=JdbcType.VARCHAR,comment="应用版本号")
	private String app_version;
	
	/**
	 * 商品名称
	 */
	@Column(name="subject",nullable=false,jdbcType=JdbcType.VARCHAR,comment="商品名称")
	private String subject;
	
	/**
	 * 商品描述
	 */
	@Column(name="description",nullable=false,jdbcType=JdbcType.VARCHAR,comment="商品描述")
	private String description;
	
	/**
	 * 商品订单号
	 */
	@Column(name="order_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="商品订单号")
	private Long order_id;
	
	/**
	 * 初始价格
	 */
	@Column(name="initial_price",nullable=true,jdbcType=JdbcType.BIGINT,comment="初始价格")
	private Long initial_price;
	
	/**
	 * 支付金额(分)
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.BIGINT,comment="支付金额(分)")
	private Long money;
	
	/**
	 * 支付交易号
	 */
	@Column(name="trade_no",nullable=true,jdbcType=JdbcType.VARCHAR,comment="支付交易号")
	private String trade_no;
	
	/**
	 * 交易状态
	 */
	@Column(name="trade_status",nullable=true,jdbcType=JdbcType.VARCHAR,comment="交易状态")
	private String trade_status;
	
	/**
	 * 支付完成时间
	 */
	@Column(name="pay_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="支付完成时间")
	private Date pay_time;
	
	/**
	 * 买家用户号
	 */
	@Column(name="buyer_id",nullable=true,jdbcType=JdbcType.VARCHAR,comment="买家用户号")
	private String buyer_id;
	
	/**
	 * 买家支付宝账号
	 */
	@Column(name="buyer_email",nullable=true,jdbcType=JdbcType.VARCHAR,comment="买家支付宝账号")
	private String buyer_email;
	
	/**
	 * 支付状态：-1失败,1成功,0支付中
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="支付状态：-1失败,1成功,0支付中")
	private Integer status;
	
	/**
	 * 支付渠道标识
	 */
	@Column(name="pay_channel",nullable=false,jdbcType=JdbcType.TINYINT,comment="支付渠道标识")
	private Integer pay_channel;
	
	/**
	 * 商品类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="商品类型")
	private Integer type;
	
	/**
	 * IAP收据MD5
	 */
	@Column(name="iap_receipt_md5",nullable=true,jdbcType=JdbcType.VARCHAR,comment="IAP收据MD5")
	private String iap_receipt_md5;
	
	/**
	 * 0 正式环境 1 沙盒环境
	 */
	@Column(name="iap_sandbox",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 正式环境 1 沙盒环境")
	private Integer iap_sandbox;
	
	/**
	 * 包名
	 */
	@Column(name="app_package",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String app_package;
	
	/**
	 * 充值次数
	 */
	@Column(name="times",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值次数")
	private Integer times;
	
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
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getApp_type() {
		return app_type;
	}

	public void setApp_type(Integer app_type) {
		this.app_type = app_type;
	}
	
	public String getApp_channel() {
		return app_channel;
	}

	public void setApp_channel(String app_channel) {
		this.app_channel = app_channel;
	}
	
	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	public Long getInitial_price() {
		return initial_price;
	}

	public void setInitial_price(Long initial_price) {
		this.initial_price = initial_price;
	}
	
	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
	
	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	
	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	
	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getPay_channel() {
		return pay_channel;
	}

	public void setPay_channel(Integer pay_channel) {
		this.pay_channel = pay_channel;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getIap_receipt_md5() {
		return iap_receipt_md5;
	}

	public void setIap_receipt_md5(String iap_receipt_md5) {
		this.iap_receipt_md5 = iap_receipt_md5;
	}
	
	public Integer getIap_sandbox() {
		return iap_sandbox;
	}

	public void setIap_sandbox(Integer iap_sandbox) {
		this.iap_sandbox = iap_sandbox;
	}
	
	public String getApp_package() {
		return app_package;
	}

	public void setApp_package(String app_package) {
		this.app_package = app_package;
	}
	
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
}