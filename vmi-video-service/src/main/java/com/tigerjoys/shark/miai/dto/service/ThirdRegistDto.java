package com.tigerjoys.shark.miai.dto.service;

import javax.validation.constraints.NotNull;

/**
 * 第三方登录传递过来的参数Dto
 * @author lipeng
 *
 */
public class ThirdRegistDto {
	
	/**
	 * open id
	 */
	private String openid;
	
	/**
	 * union id 仅适用于QQ
	 */
	private String unionid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 城市
	 */
	private String cityName;
	
	/**
	 * 城市
	 */
	private Integer cityCode;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 邀请码
	 */
	private String inviteCode;
	
	/**
	 * 经度
	 */
	private Double lng;
	
	/**
	 * 纬度
	 */
	private Double lat;
	
	/**
	 * 注册来源,1微信注册,2QQ注册3手机4游客
	 */
	@NotNull
	private Integer fr;
	
	/**
	 * 对性的看法
	 */
	private int sexOpinion;
	
	/**
	 * 对另一半的看法
	 */
	private int spouseOpinion;
	
	/**
	 * 交友目地
	 */
	private int makeFriend;
	
	/**
     * 用户是否root 1 root 0 未 root
     */
    private int vip;
    /**
     * 用户id
     */
    private long time;
	
	
    /**
	 * 是否是注册
	 */
	private Boolean ifRegLogin;
	
	/**
	 * 推送渠道标识 1 小米 2 华为 3 VIVO
	 */
	private Integer pushchannel;
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getFr() {
		return fr;
	}

	public void setFr(Integer fr) {
		this.fr = fr;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
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

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Boolean getIfRegLogin() {
		return ifRegLogin;
	}

	public void setIfRegLogin(Boolean ifRegLogin) {
		this.ifRegLogin = ifRegLogin;
	}

	public int getSexOpinion() {
		return sexOpinion;
	}

	public void setSexOpinion(int sexOpinion) {
		this.sexOpinion = sexOpinion;
	}

	public int getSpouseOpinion() {
		return spouseOpinion;
	}

	public void setSpouseOpinion(int spouseOpinion) {
		this.spouseOpinion = spouseOpinion;
	}

	public int getMakeFriend() {
		return makeFriend;
	}

	public void setMakeFriend(int makeFriend) {
		this.makeFriend = makeFriend;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	public Integer getPushchannel() {
		return pushchannel;
	}

	public void setPushchannel(Integer pushchannel) {
		this.pushchannel = pushchannel;
	}

	
}
