package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户数据信息DTO
 * @author yangjunming
 *
 */
public class UserBaseInfo {
	
	/*-----------共公数据------------*/
	/**
	 * 用户ID
	 */
	private long userId;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 头象
	 */
	private String photo;
	
	/**
	 * 城市ID
	 */
	private String cityId;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 用户类型  0  普通用户 1 主播
	 */
	private int  userType ;
	
	
	/*-----------主播数据------------*/
	
	/**
	 * 主播状态
	 */
	private int anchorStatus;
	
	/**
	 * 主播星级
	 */
	private int anchorStar;
	
	/**
	 *  主播价格（12V币/分钟）
	 */
	private String  anchorPrice;
	
	 /**
     * 主播价格（12V币/分钟）音频
     */
    private String anchorAudioPrice;

    /**
     * 是否是音频主播 0不是 1是
     */
    private int anchorTypeAudio;

    /**
     * 是否是视频主播 0不是 1是
     */
    private int anchorTypeVideo;

	
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
	 * 个人介绍
	 */
	private String anchorIntr;
	
	/**
	 * 主播粉丝数
	 */
	private Integer anchorFans;
	
	/**
	 * 主播标签
     */
    private String anchorTag;

    /**
      * 主播标签背景
     */
    private String anchorTagBackground;
	

	/*-----------普通用户数据------------*/

	/**
	 * 普通用户等级
	 */
	private String normalLevel;
	
	
	/**
     * 用户余额
     */
    private String balance;
    
    
    /**
     * 备用信息1
     */
    private String backText1;
    
    
    
    /**
     * 备用信息2
     */
    private String backText2;
    
    
    
    /**
     * 备用信息3
     */
    private String backText3;

    /**
     * 用户在线状态
     * 0 离线 1 勿扰 2 在聊 3 在线
     */
    private int onlineStatue;
    
    


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getAnchorStatus() {
		return anchorStatus;
	}

	public void setAnchorStatus(int anchorStatus) {
		this.anchorStatus = anchorStatus;
	}

	public int getAnchorStar() {
		return anchorStar;
	}

	public void setAnchorStar(int anchorStar) {
		this.anchorStar = anchorStar;
	}

	public String getAnchorPrice() {
		return anchorPrice;
	}

	public void setAnchorPrice(String anchorPrice) {
		this.anchorPrice = anchorPrice;
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

	public String getAnchorIntr() {
		return anchorIntr;
	}
	
	public void setAnchorIntr(String anchorIntr) {
		this.anchorIntr = anchorIntr;
	}
	
	public Integer getAnchorFans() {
		return anchorFans;
	}
	
	public void setAnchorFans(Integer anchorFans) {
		this.anchorFans = anchorFans;
	}
	
	public String getNormalLevel() {
		return normalLevel;
	}

	public void setNormalLevel(String normalLevel) {
		this.normalLevel = normalLevel;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBackText1() {
		return backText1;
	}

	public void setBackText1(String backText1) {
		this.backText1 = backText1;
	}

	public String getBackText2() {
		return backText2;
	}

	public void setBackText2(String backText2) {
		this.backText2 = backText2;
	}

	public String getBackText3() {
		return backText3;
	}

	public void setBackText3(String backText3) {
		this.backText3 = backText3;
	}

	public String getAnchorAudioPrice() {
		return anchorAudioPrice;
	}

	public void setAnchorAudioPrice(String anchorAudioPrice) {
		this.anchorAudioPrice = anchorAudioPrice;
	}

	public int getAnchorTypeAudio() {
		return anchorTypeAudio;
	}

	public void setAnchorTypeAudio(int anchorTypeAudio) {
		this.anchorTypeAudio = anchorTypeAudio;
	}

	public int getAnchorTypeVideo() {
		return anchorTypeVideo;
	}

	public void setAnchorTypeVideo(int anchorTypeVideo) {
		this.anchorTypeVideo = anchorTypeVideo;
	}

	public String getAnchorTag() {
		return anchorTag;
	}

	public void setAnchorTag(String anchorTag) {
		this.anchorTag = anchorTag;
	}

	public String getAnchorTagBackground() {
		return anchorTagBackground;
	}

	public void setAnchorTagBackground(String anchorTagBackground) {
		this.anchorTagBackground = anchorTagBackground;
	}

	public int getOnlineStatue() {
		return onlineStatue;
	}

	public void setOnlineStatue(int onlineStatue) {
		this.onlineStatue = onlineStatue;
	}
	
}
