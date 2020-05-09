package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 首页推荐达人列表VO对象
 * @author shiming
 *
 */
public class RecommendTalentListVO implements Serializable {

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
	 * 认证标识: 0-未认证，1-认证
	 */
	private int identification;
	
	/**
	 * 是否为vip用户:大于0就是vip
	 */
	private int isVip;
	
	/**
	 * 是否有形象视频: 0-无，1-有
	 */
	private int isVideo;
	
	/**
     * 达人图标
     */
    private String masterPic;
    
    /**
     * 标签
     */
    private String tabText;
    
    /**
     * 视频路径
     */
    private String videoUrl;

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

	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public int getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(int isVideo) {
		this.isVideo = isVideo;
	}

	public String getMasterPic() {
		return masterPic;
	}

	public void setMasterPic(String masterPic) {
		this.masterPic = masterPic;
	}

	public String getTabText() {
		return tabText;
	}

	public void setTabText(String tabText) {
		this.tabText = tabText;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
}
