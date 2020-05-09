package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 约她推荐达人列表VO对象
 * @author shiming
 */
public class RecommendAppointListVO implements Serializable {

	private static final long serialVersionUID = -2011248918802355541L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 所在城市
	 */
	private String cityName;
	
	/**
	 * 性别:1-男,2-女
	 */
	private int gender;
	
	/**
	 * 年龄
	 */
	private int age;
	
	 /**
     * 个性签名
     */
    private String signaName;
    
    /**
     * 在线状态 1 在线 0 离线
     */
    private int onlineStatue;
    
    /**
     * 评分
     */
    private int star;
    

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSignaName() {
		return signaName;
	}

	public void setSignaName(String signaName) {
		this.signaName = signaName;
	}

	public int getOnlineStatue() {
		return onlineStatue;
	}

	public void setOnlineStatue(int onlineStatue) {
		this.onlineStatue = onlineStatue;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
}
