package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户黑名单信息
 * @author lipeng
 *
 */
public class UserBlacklistVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9220385837869986258L;

	/**
	 * 主键ID
	 */
	private long id;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像(大图)
	 */
	private String photo;
	
	/**
	 * 用户头像(小图)
	 */
	private String photoSmall;
	
	/**
	 * 性别，1男，2女
	 */
	private int sex;
	
	/**
	 * 年龄
	 */
	private int age;
	
	/**
	 * 是否达人VIP
	 */
	private boolean talentVip;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
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

	public String getPhotoSmall() {
		return photoSmall;
	}

	public void setPhotoSmall(String photoSmall) {
		this.photoSmall = photoSmall;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isTalentVip() {
		return talentVip;
	}

	public void setTalentVip(boolean talentVip) {
		this.talentVip = talentVip;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	
}
