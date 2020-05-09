package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 微信用户个人主页资料详情VO
 * @author lipeng
 *
 */
public class WeixinUserHomePageVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6338282347918704960L;

	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 头象
	 */
	private String photo;
	
	/**
	 * 身高
	 */
	private String anchorStature;
	
	/**
	 * 体重
	 */
	private String anchorWeight;
	
	/**
	 * 星座
	 */
	private String anchorZodiac;
	
	/**
	 * 个性签名
	 */
	private String anchorSignature;
	
	/**
	 * 主播粉丝数
	 */
	private Integer anchorFans;
	
	/**
	 * 主播标签
     */
    private String anchorTag;
    
    /**
	 * 主播星级
	 */
	private Integer anchorStar;

	/**
	 * 用户评价list
	 */
	private List<UserEvaluationDto> userEvaluationList;
	
	/**
	 * 个人资料
	 */
	private Map<String ,String> userInfo;
	
    /**
     * 年龄|距离
     */
    private String ageDistance;
    
    /**
     * 是否显示联系方式 0不显示 1显示
     */
    private Integer showContact;
    
    /**
     * 相册列表
     */
    private List<UserPhotoDto> albumList;
    
    /**
     * 视频列表
     */
    private List<UserVideoDto> videoList;


	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAnchorStature() {
		return anchorStature;
	}

	public void setAnchorStature(String anchorStature) {
		this.anchorStature = anchorStature;
	}

	public String getAnchorWeight() {
		return anchorWeight;
	}

	public void setAnchorWeight(String anchorWeight) {
		this.anchorWeight = anchorWeight;
	}

	public String getAnchorZodiac() {
		return anchorZodiac;
	}

	public void setAnchorZodiac(String anchorZodiac) {
		this.anchorZodiac = anchorZodiac;
	}

	public String getAnchorSignature() {
		return anchorSignature;
	}

	public void setAnchorSignature(String anchorSignature) {
		this.anchorSignature = anchorSignature;
	}

	public Integer getAnchorFans() {
		return anchorFans;
	}

	public void setAnchorFans(Integer anchorFans) {
		this.anchorFans = anchorFans;
	}

	public String getAnchorTag() {
		return anchorTag;
	}

	public void setAnchorTag(String anchorTag) {
		this.anchorTag = anchorTag;
	}

	public List<UserEvaluationDto> getUserEvaluationList() {
		return userEvaluationList;
	}

	public void setUserEvaluationList(List<UserEvaluationDto> userEvaluationList) {
		this.userEvaluationList = userEvaluationList;
	}

	public String getAgeDistance() {
		return ageDistance;
	}

	public void setAgeDistance(String ageDistance) {
		this.ageDistance = ageDistance;
	}

	public Map<String, String> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Map<String, String> userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getAnchorStar() {
		return anchorStar;
	}

	public void setAnchorStar(Integer anchorStar) {
		this.anchorStar = anchorStar;
	}

	public Integer getShowContact() {
		return showContact;
	}

	public void setShowContact(Integer showContact) {
		this.showContact = showContact;
	}

	public List<UserPhotoDto> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<UserPhotoDto> albumList) {
		this.albumList = albumList;
	}

	public List<UserVideoDto> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<UserVideoDto> videoList) {
		this.videoList = videoList;
	}

}
