package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户背景图片信息DTO
 * @author lipeng
 *
 */
public class UserBgPictureBO  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8324783378325261020L;

	/**
	 * ID
	 */
	private long id;
	
	/**
	 * 图片地址
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
