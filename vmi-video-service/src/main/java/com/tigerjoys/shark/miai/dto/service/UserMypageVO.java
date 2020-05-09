package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 我的页面接口详情
 * @author lipeng
 *
 */
public class UserMypageVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5122285056844567913L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 用户主页详情
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	 /**
     * 背景图片
     */
    private String bgPicture;
    
    /**
	 * 是否是大V 0不是 1是 2审核
	 */
	private Integer isV;
    
	/**
	 * 是否填写过邀请码 0否 1是 
	 */
	private Integer isInvite;
    
	/**
	 * 是否有音频 0没有 1有 普通用户不发
	 */
	private Integer audioStatus;
	
	 /**
     * 审核文字
     */
    private String verifyText;
    
    /**
   	 * 是否是大Vip 0不是 1是
   	 */
   	private Integer vip;
	
   	/**
   	 * 是否显示我的钱包 0 不显示 1显示
   	 */
   	private Integer walletShow;
   	
   	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
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

	public String getBgPicture() {
		return bgPicture;
	}

	public void setBgPicture(String bgPicture) {
		this.bgPicture = bgPicture;
	}
	
	public Integer getIsV() {
		return isV;
	}

	public void setIsV(Integer isV) {
		this.isV = isV;
	}

	public Integer getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(Integer isInvite) {
		this.isInvite = isInvite;
	}

	public Integer getAudioStatus() {
		return audioStatus;
	}

	public void setAudioStatus(Integer audioStatus) {
		this.audioStatus = audioStatus;
	}

	public String getVerifyText() {
		return verifyText;
	}

	public void setVerifyText(String verifyText) {
		this.verifyText = verifyText;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Integer getWalletShow() {
		return walletShow;
	}

	public void setWalletShow(Integer walletShow) {
		this.walletShow = walletShow;
	}

	
}
