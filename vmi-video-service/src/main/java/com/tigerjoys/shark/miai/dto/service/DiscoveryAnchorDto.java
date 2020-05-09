package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 发现主播列表VO对象
 * @author shiming
 */
public class DiscoveryAnchorDto implements Serializable {

	/**
	 * 用户ID
	 */
	private String userid;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
