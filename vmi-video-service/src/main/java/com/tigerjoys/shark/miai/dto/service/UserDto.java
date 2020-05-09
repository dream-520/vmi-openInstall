package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户dto
 * @author chengang
 *
 */
public class UserDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5866110543408231810L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	/**
	 * 性别，1男，2女，0未知
	 */
	private int sex;
	
	/**
	 * 状态，1正常,0查封
	 */
	private int status;
	
	/**
	 * 注册方式,0系统注册
	 */
	private int fr;
	


	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
	}


}
