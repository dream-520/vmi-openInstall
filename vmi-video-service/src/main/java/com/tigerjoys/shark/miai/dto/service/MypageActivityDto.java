package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 我的页面活动详情Dto
 * @author lipeng
 *
 */
public class MypageActivityDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8820365163959763599L;

	/**
	 * 活动栏目目录标号
	 */
	private Integer indexCode;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 活动地址
	 */
	private String url;
	
	/**
	 * 活动图标
	 */
	private String icon;

	public Integer getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(Integer indexCode) {
		this.indexCode = indexCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
