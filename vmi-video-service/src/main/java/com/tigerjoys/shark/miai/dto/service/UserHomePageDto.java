package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户个人主页资料详情Dto
 * @author lipeng
 *
 */
public class UserHomePageDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6157548648706060394L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * idcard
	 */
	private int idcard;
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
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 所属城市
	 */
	private long cityId;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 魅力值
	 */
	private int charm;
	
	/**
	 * 是否已关注
	 */
	private Boolean isAttention;
	
	/**
	 * 是否验证联系方式
	 */
	private Boolean isContactInfo;
	
	/**
	 * 联系方式状态是否显示
	 */
	private Boolean isContactStatus;
	
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
	 * 信用保障
	 */
	private Boolean isCreditAssu;
	
	/**
	 * 是否是vip
	 */
	private Boolean isVip;
	
	 /**
     * 关注数
     */
    private int attentionCount;

    /**
     * 粉丝数
     */
    private int fansCount;
	
	/**
	 * 是否认证视频
	 */
	private Boolean isVideoAuth;
	
	/**
	 * 是否认证
	 */
	private Boolean isAuth;
	
	/**
	 * 认证视频
	 */
	private String videoAuthUrl;
	
	/**
	 * 交友目地
	 */
	private String makeFriend;

	/**
	 * 是否在黑名单中
	 */
	private Boolean isInBlackList;
	
	/**
     * 背景图片
     */
    private String bgImage;

    /**
     * 形象视频
     */
    private String video;

    /**
     * 用户头像-大图
     */
    private String photoBig;

    /**
     * 隐私设置，0达人秀可以看，1不能看
     */
    private int privacy;
    
    /**
    * 是否是达人,0不是；>0表示达人等级
    */
   private int talentLevelId;
   
   /**
    * 达人等级名称
    */
   private String talentLevelName;
   
   /**
    * 是否可以观看形象视频,0不能看；1能看
    */
   private int canSeeVideo;

   /**
    * 达人等级图片
    */
   private String talentLevelImgUrl;
   
   /**
    * 是否有形象视频：1有；0没有
    */
   private int hasVideo;
   

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getIdcard() {
		return idcard;
	}

	public void setIdcard(int idcard) {
		this.idcard = idcard;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
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

	public int getCharm() {
		return charm;
	}

	public void setCharm(int charm) {
		this.charm = charm;
	}

	public Boolean getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Boolean isAttention) {
		this.isAttention = isAttention;
	}

	public Boolean getIsContactInfo() {
		return isContactInfo;
	}

	public void setIsContactInfo(Boolean isContactInfo) {
		this.isContactInfo = isContactInfo;
	}

	public Boolean getIsContactStatus() {
		return isContactStatus;
	}

	public void setIsContactStatus(Boolean isContactStatus) {
		this.isContactStatus = isContactStatus;
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

	public Boolean getIsCreditAssu() {
		return isCreditAssu;
	}

	public void setIsCreditAssu(Boolean isCreditAssu) {
		this.isCreditAssu = isCreditAssu;
	}

	public Boolean getIsVip() {
		return isVip;
	}

	public void setIsVip(Boolean isVip) {
		this.isVip = isVip;
	}

	public int getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public Boolean getIsVideoAuth() {
		return isVideoAuth;
	}

	public void setIsVideoAuth(Boolean isVideoAuth) {
		this.isVideoAuth = isVideoAuth;
	}

	public String getVideoAuthUrl() {
		return videoAuthUrl;
	}

	public void setVideoAuthUrl(String videoAuthUrl) {
		this.videoAuthUrl = videoAuthUrl;
	}

	public String getMakeFriend() {
		return makeFriend;
	}

	public void setMakeFriend(String makeFriend) {
		this.makeFriend = makeFriend;
	}

	public Boolean getIsInBlackList() {
		return isInBlackList;
	}

	public void setIsInBlackList(Boolean isInBlackList) {
		this.isInBlackList = isInBlackList;
	}

	public Boolean getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getPhotoBig() {
		return photoBig;
	}

	public void setPhotoBig(String photoBig) {
		this.photoBig = photoBig;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public int getTalentLevelId() {
		return talentLevelId;
	}

	public void setTalentLevelId(int talentLevelId) {
		this.talentLevelId = talentLevelId;
	}

	public String getTalentLevelName() {
		return talentLevelName;
	}

	public void setTalentLevelName(String talentLevelName) {
		this.talentLevelName = talentLevelName;
	}

	public int getCanSeeVideo() {
		return canSeeVideo;
	}

	public void setCanSeeVideo(int canSeeVideo) {
		this.canSeeVideo = canSeeVideo;
	}

	public String getTalentLevelImgUrl() {
		return talentLevelImgUrl;
	}

	public void setTalentLevelImgUrl(String talentLevelImgUrl) {
		this.talentLevelImgUrl = talentLevelImgUrl;
	}

	public int getHasVideo() {
		return hasVideo;
	}

	public void setHasVideo(int hasVideo) {
		this.hasVideo = hasVideo;
	}

}
