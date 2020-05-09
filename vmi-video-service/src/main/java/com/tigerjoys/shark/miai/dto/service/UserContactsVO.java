package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户联系方式详情VO
 * @author lipeng
 *
 */
public class UserContactsVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7550808164494965455L;

	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * QQ号码
	 */
	private String qq;
	
	/**
	 * 微信号码
	 */
	private String weixin;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	
}
