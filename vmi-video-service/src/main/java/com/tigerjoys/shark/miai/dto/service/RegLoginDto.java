package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户注册登录dto
 * @author lipeng
 *
 */
/**
 * @author lipeng
 *
 */
public class RegLoginDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6255267113386338855L;
	
	/**
	 * 用户id
	 */
	private DynamicUserDataDto  dynamicData ;
	
	/**
	 * 用户id
	 */
	private long userid;
	
	/**
	 * idcard
	 */
	private String idcard;
	
	/**
	 * 头像
	 */
	private String photo;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 用户名
	 */
	private String openid;
	
	/**
	 * 是否需要完善基本个人资料, 0:不需要；1需要
	 */
	private int needToProfile;
	
	/**
	 * 性别
	 */
	private int sex;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 个性签名
	 */
	private String signature;
	/**
	 * 是否是第一次登录
	 */
	private Boolean ifReg; 
	
	/**
	 * 是否是vip
	 */
	private Boolean ifVip;
	
	/**
	 * vip状态   0不是；1是；2已过期
	 */
	private int vipStatus;
	
	/**
	 * 用户聊天id
	 */
	private String imId;
	/**
	 * 用户聊天密码
	 */
	private String imPass;
	/**
	 * 用户登录token
	 */
	private String token;
	
	/**
	 * 经度
	 */
	private Double lng;
	
	/**
	 * 纬度
	 */
	private Double lat;
	
	/**
	 * 注册来源,1微信注册,2QQ注册,3手机注册
	 */
	private Integer fr;
	
	 /**
     * 隐私设置，0达人秀可以看，1不能看
     */
    private int privacy;
    
    /**
	 * 用户聊天id
	 */
	private String txId;
	/**
	 * 用户聊天密码
	 */
	private String txPass;
	
	/**
	 * 是否显示收益状态 0否 1是
	 */
	private Integer incomeStatus;
	
	/**
	 * 用户类型   0普通用户   1主播
	 */
	private Integer userType;
	
	  /**
     * 是否是音频主播 0不是 1是
     */
    private Integer anchorTypeAudio;

    /**
     * 是否是视频主播 0不是 1是
     */
    private Integer anchorTypeVideo;

	
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getImId() {
		return imId;
	}
	public void setImId(String imId) {
		this.imId = imId;
	}
	public String getImPass() {
		return imPass;
	}
	public void setImPass(String imPass) {
		this.imPass = imPass;
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
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIfReg() {
		return ifReg;
	}
	public void setIfReg(Boolean ifReg) {
		this.ifReg = ifReg;
	}
	public int getVipStatus() {
		return vipStatus;
	}
	public void setVipStatus(int vipStatus) {
		this.vipStatus = vipStatus;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getNeedToProfile() {
		return needToProfile;
	}
	public void setNeedToProfile(int needToProfile) {
		this.needToProfile = needToProfile;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Integer getFr() {
		return fr;
	}
	public void setFr(Integer fr) {
		this.fr = fr;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	public Boolean getIfVip() {
		return ifVip;
	}
	public void setIfVip(Boolean ifVip) {
		this.ifVip = ifVip;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public String getTxPass() {
		return txPass;
	}
	public void setTxPass(String txPass) {
		this.txPass = txPass;
	}
	public Integer getIncomeStatus() {
		return incomeStatus;
	}
	public void setIncomeStatus(Integer incomeStatus) {
		this.incomeStatus = incomeStatus;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAnchorTypeAudio() {
		return anchorTypeAudio;
	}
	public void setAnchorTypeAudio(Integer anchorTypeAudio) {
		this.anchorTypeAudio = anchorTypeAudio;
	}
	public Integer getAnchorTypeVideo() {
		return anchorTypeVideo;
	}
	public void setAnchorTypeVideo(Integer anchorTypeVideo) {
		this.anchorTypeVideo = anchorTypeVideo;
	}
	public DynamicUserDataDto getDynamicData() {
		return dynamicData;
	}
	public void setDynamicData(DynamicUserDataDto dynamicData) {
		this.dynamicData = dynamicData;
	}
	
	
}
