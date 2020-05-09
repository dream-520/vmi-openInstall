package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户黑名单信息
 * @author lipeng
 *
 */
public class PrivacySetVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9220385837869986258L;

	
	
	/**
	 * 隐私开关状态 0 关 1开
	 */
	private Integer privacyStatus;
	
	/**
	 * 达人秀开关 0关 1开
	 */
	private Integer talentStatus;
	
	/**
	 * 密码状态 0没有 1 有
	 */
	private Integer passwordStatus;
	
	/**
	 * 解释语
	 */
	private String explain;

	public Integer getPrivacyStatus() {
		return privacyStatus;
	}

	public void setPrivacyStatus(Integer privacyStatus) {
		this.privacyStatus = privacyStatus;
	}

	public Integer getTalentStatus() {
		return talentStatus;
	}

	public void setTalentStatus(Integer talentStatus) {
		this.talentStatus = talentStatus;
	}

	public Integer getPasswordStatus() {
		return passwordStatus;
	}

	public void setPasswordStatus(Integer passwordStatus) {
		this.passwordStatus = passwordStatus;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
