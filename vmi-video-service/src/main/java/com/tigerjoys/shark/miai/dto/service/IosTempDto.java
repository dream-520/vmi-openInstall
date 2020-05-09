package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * ios临时dto
 * @author lipeng
 *
 */
public class IosTempDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -915763274673167140L;

	private String title;
	
	private String icon;
	
	private Integer pushType;
	
	private String url;

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

	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
