package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户Ta的约会标题图标VO
 * @author lipeng
 *
 */
public class UserAppointVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7550808164494965455L;

	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 约会ID
	 */
	private long appointId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public long getAppointId() {
		return appointId;
	}

	public void setAppointId(long appointId) {
		this.appointId = appointId;
	}

	
}
