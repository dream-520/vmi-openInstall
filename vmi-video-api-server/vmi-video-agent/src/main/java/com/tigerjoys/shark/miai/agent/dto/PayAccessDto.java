package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;

/** 
  * @author mouzhanpeng at [2017年10月10日 上午11:36:33] 
  * @since JDK 1.8.0 
  */
public class PayAccessDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1378597529968571449L;

	/**
	 * 用户ID
	 */
	private Long user_id;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 系统类型，1安卓，2苹果
	 */
	private Integer app_type;
	
	/**
	 * 应用渠道名称
	 */
	private String app_channel;
	
	/**
	 * 应用版本号
	 */
	private String app_version;
	
	/**
	 * 应用版本号
	 */
	private String package_name;
	
	/**
	 * 商品名称
	 */
	private String subject;
	
	/**
	 * 商品描述
	 */
	private String description;
	
	/**
	 * 商品订单号
	 */
	private Long order_id;
	
	/**
	 * 初始价格(分)
	 */
	private Long initialPrice;
	
	/**
	 * 支付金额(分)
	 */
	private Long money;
	
	/**
	 * 支付渠道标识
	 */
	private PayChannelEnum pay_channel;
	
	/**
	 * 商品类型
	 */
	private PayTypeEnum type;
	
	/**
	 * IAP收据
	 */
	private String receipt;
	
	/**
	 * IAP流水号
	 */
	private String ipaTransactionId;
	
	/**
	 * IAP商品标识
	 */
	private String product_id;
	
	/**
	 * 扩展字段
	 */
	private String extra;
	
	/**
	 * 微信JS支出需要此值
	 */
	private String openId;

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

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
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

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public PayChannelEnum getPay_channel() {
		return pay_channel;
	}

	public void setPay_channel(PayChannelEnum pay_channel) {
		this.pay_channel = pay_channel;
	}

	public PayTypeEnum getType() {
		return type;
	}

	public void setType(PayTypeEnum type) {
		this.type = type;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Long getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Long initialPrice) {
		this.initialPrice = initialPrice;
	}

	public String getIpaTransactionId() {
		return ipaTransactionId;
	}

	public void setIpaTransactionId(String ipaTransactionId) {
		this.ipaTransactionId = ipaTransactionId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
