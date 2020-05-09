package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户基本信息VO对象
 * @author chengang
 *
 */
public class UserBasicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6560423370944415111L;
	
	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * VIP等级,默认0
	 */
	private Integer vip;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 头像
	 */
	private String head;
	
	/**
	 * 大头像
	 */
	private String bigHead;
	
	/**
	 * 视频认证地址
	 */
	private String videoAuth;
	
	/**
	 * 城市ID
	 */
	private Integer cityid;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 性别,1男,2女
	 */
	private Integer gender;
	
	/**
	 * 是否达人VIP
	 */
	private Integer talentVip;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 达人等级
	 */
	private Integer talentLevelId;
	
	/**
	 * 达人等级名称
	 */
	private String talentLevelName;
	
	/**
	 * 达人图片
	 */
	private String talentLevelImgUrl;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBigHead() {
		return bigHead;
	}

	public void setBigHead(String bigHead) {
		this.bigHead = bigHead;
	}

	public String getVideoAuth() {
		return videoAuth;
	}

	public void setVideoAuth(String videoAuth) {
		this.videoAuth = videoAuth;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getTalentVip() {
		return talentVip;
	}

	public void setTalentVip(Integer talentVip) {
		this.talentVip = talentVip;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getTalentLevelId() {
		return talentLevelId;
	}

	public void setTalentLevelId(Integer talentLevelId) {
		this.talentLevelId = talentLevelId;
	}

	public String getTalentLevelName() {
		return talentLevelName;
	}

	public void setTalentLevelName(String talentLevelName) {
		this.talentLevelName = talentLevelName;
	}

	public String getTalentLevelImgUrl() {
		return talentLevelImgUrl;
	}

	public void setTalentLevelImgUrl(String talentLevelImgUrl) {
		this.talentLevelImgUrl = talentLevelImgUrl;
	}
	
}
