package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户资料详情dto
 * @author lipeng
 *
 */
public class UserDetailDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5122285056844567913L;

	/**
	 * 用户ID
	 */
	private long userid;
	
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
	private Integer sex;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 所属城市
	 */
	private Integer cityId;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * QQ号码
	 */
	private String qq;
	
	/**
	 * 微信号码
	 */
	private String weixin;
	
	/**
	 * 联系方式状态：1显示,2不显示
	 */
	private Integer contact_status;
	
	/**
	 * 星座
	 */
	private String zodiac;
	
	/**
	 * 感情状态id
	 */
	private Integer marriage;
	
	/**
	 * 感情状态描述
	 */
	private String marriageDesc;
	
	/**
	 * 职位
	 */
	private String job;

	/**
	 * 收入
	 */
	private Integer income;
	
	/**
	 * 收入描述
	 */
	private String incomeDesc;
	
	/**
	 * 身高(CM)
	 */
	private Integer stature;
	
	/**
	 * 体重(KG)
	 */
	private Integer weight;
	
	/**
	 * 对性的看法
	 */
	private Integer sexOpinion;
	
	/**
	 * 对性的看法描述
	 */
	private String sexOpinionDesc;
	
	/**
	 * 对另一半的看法
	 */
	private Integer spouseOpinion;
	
	/**
	 * 对另一半的看法描述
	 */
	private String spouseOpinionDesc;
	
	/**
	 * 交友目地
	 */
	private Integer makeFriend;
	
	/**
	 * 交友目地描述
	 */
	private String makeFriendDesc;
	
	/**
	 * 特点
	 */
	private String traitPoint;
	
	/**
     * 形象视频
     */
    private String videoPath;
    
    /**
     * 形象视频图片
     */
    private String videoPicture;
    
    /**
     * 背景图片
     */
    private String bgPicture;
    
    /**
     * 个性签名
     */
    private String anchorSignature;

    /**
     * 个人介绍
     */
    private String anchorIntr;

    /**
     * 个人介绍
     */
    private String verifyText;
    
    
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getContact_status() {
		return contact_status;
	}

	public void setContact_status(Integer contact_status) {
		this.contact_status = contact_status;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public String getMarriageDesc() {
		return marriageDesc;
	}

	public void setMarriageDesc(String marriageDesc) {
		this.marriageDesc = marriageDesc;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public String getIncomeDesc() {
		return incomeDesc;
	}

	public void setIncomeDesc(String incomeDesc) {
		this.incomeDesc = incomeDesc;
	}

	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getSexOpinion() {
		return sexOpinion;
	}

	public void setSexOpinion(Integer sexOpinion) {
		this.sexOpinion = sexOpinion;
	}

	public String getSexOpinionDesc() {
		return sexOpinionDesc;
	}

	public void setSexOpinionDesc(String sexOpinionDesc) {
		this.sexOpinionDesc = sexOpinionDesc;
	}

	public Integer getSpouseOpinion() {
		return spouseOpinion;
	}

	public void setSpouseOpinion(Integer spouseOpinion) {
		this.spouseOpinion = spouseOpinion;
	}

	public String getSpouseOpinionDesc() {
		return spouseOpinionDesc;
	}

	public void setSpouseOpinionDesc(String spouseOpinionDesc) {
		this.spouseOpinionDesc = spouseOpinionDesc;
	}

	public Integer getMakeFriend() {
		return makeFriend;
	}

	public void setMakeFriend(Integer makeFriend) {
		this.makeFriend = makeFriend;
	}

	public String getMakeFriendDesc() {
		return makeFriendDesc;
	}

	public void setMakeFriendDesc(String makeFriendDesc) {
		this.makeFriendDesc = makeFriendDesc;
	}

	public String getTraitPoint() {
		return traitPoint;
	}

	public void setTraitPoint(String traitPoint) {
		this.traitPoint = traitPoint;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getVideoPicture() {
		return videoPicture;
	}

	public void setVideoPicture(String videoPicture) {
		this.videoPicture = videoPicture;
	}

	public String getBgPicture() {
		return bgPicture;
	}

	public void setBgPicture(String bgPicture) {
		this.bgPicture = bgPicture;
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

	public String getVerifyText() {
		return verifyText;
	}

	public void setVerifyText(String verifyText) {
		this.verifyText = verifyText;
	}

}
