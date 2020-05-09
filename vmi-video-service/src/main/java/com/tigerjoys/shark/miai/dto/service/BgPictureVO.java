package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 我的页面背景图片接口详情
 * @author lipeng
 *
 */
public class BgPictureVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4794779400623956059L;

	/**
	 * 图片ID
	 */
	private long id;
	
	/**
	 * 图片url
	 */
	private String url;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
