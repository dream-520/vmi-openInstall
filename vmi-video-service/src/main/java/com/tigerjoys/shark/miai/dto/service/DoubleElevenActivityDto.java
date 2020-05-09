package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 双11榜单临时Dto
 * @author chengang
 *
 */
public class DoubleElevenActivityDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4665398552021812567L;
	
	private long userid;
	/**
	 * 聊天分钟数/礼物贡献数
	 */
	private int number;
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像(大图)
	 */
	private String photo;
	
	/**
	 * 聊天分钟数/礼物贡献数
	 */
	private int count;

	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
	
}
