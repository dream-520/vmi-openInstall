package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户简单对象
 * @author chengang
 *
 */
public class UserSimpleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3164600518114760081L;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	/**
	 * 用户昵称
	 */
	private String nickname;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
