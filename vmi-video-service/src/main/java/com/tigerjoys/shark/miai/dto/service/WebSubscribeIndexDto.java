package com.tigerjoys.shark.miai.dto.service;

/**
 * 一键预约首页Dto实体
 * @author shiming
 *
 */
public class WebSubscribeIndexDto {

	private String userid;
	
	private String nickname;
	
	private String photo;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
