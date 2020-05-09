package com.tigerjoys.shark.miai.dto.service;

/**
 * 一键预约首页Dto实体
 * @author shiming
 *
 */
public class WebSubscribeUserDetailDto {

	private String userid;
	
	private String nickname;
	
	private String photo;
	
	private int state;
	
	private int onlineState;
	
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(int onlineState) {
		this.onlineState = onlineState;
	}
	
}
