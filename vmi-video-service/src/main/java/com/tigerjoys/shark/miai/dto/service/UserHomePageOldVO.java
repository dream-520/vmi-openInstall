package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;

/**
 * 用户个人主页资料详情VO
 * @author lipeng
 *
 */
public class UserHomePageOldVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2373959864134874779L;
	
	/**
	 * 用户主页详情
	 */
	private UserHomePageDto userInfo;
	
	/**
	 * Ta的相册图片地址urllist
	 */
	private List<String> pictureList;
	
	/**
	 * Ta的相册图片原图地址
	 */
	private List<String> pictureOriginalList;
	
	/**
	 * ta的动态图片地址urllist
	 */
	private List<String> dynamicList;
	
	/**
	 * ta的信息list
	 */
	private List<String> extensionList;
	
	/**
	 * ta的约会list
	 */
	private List<UserAppointVO> appointList;
	
	/**
	 * 动态url
	 */
	private String dynamicUrl;
	
	/**
	 * ta的服务
	 */
	private String serve;
	
	/**
	 * ta的服务价格
	 */
	private String servePrice;

	/**
	 * ta的特点
	 */
	private String traitPoint;
	
	/**
	 * 自身是否认证过
	 */
    private Boolean isAuthOfSelf;
    
    /**
     * 自身是否是vip
     */
    private Boolean isVipOfSelf;
    
    /**
     * 是否显示视频聊按钮，0不显示，1显示
     */
    private Integer showVideoChat;
    
    /**
     * 余额是否充足，0不充足，1充足；点击视频聊的时候判断此值
     */
    private Integer balanceEnough;
    
    /**
     * 余额是否充足，0不充足，1充足；点击语音的时候判断此值
     */
    private Integer audioBalanceEnough;
    
    /**
     * 在线状态    0 离线 1 勿扰 2 在聊 3 在线
     */
    private int onlineStatue;
    
	public UserHomePageDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserHomePageDto userInfo) {
		this.userInfo = userInfo;
	}

	public List<String> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	public List<String> getDynamicList() {
		return dynamicList;
	}

	public void setDynamicList(List<String> dynamicList) {
		this.dynamicList = dynamicList;
	}

	public List<String> getExtensionList() {
		return extensionList;
	}

	public void setExtensionList(List<String> extensionList) {
		this.extensionList = extensionList;
	}

	public String getServe() {
		return serve;
	}

	public void setServe(String serve) {
		this.serve = serve;
	}
	
	public String getServePrice() {
		return servePrice;
	}

	public void setServePrice(String servePrice) {
		this.servePrice = servePrice;
	}

	public String getTraitPoint() {
		return traitPoint;
	}

	public void setTraitPoint(String traitPoint) {
		this.traitPoint = traitPoint;
	}

	public List<String> getPictureOriginalList() {
		return pictureOriginalList;
	}

	public void setPictureOriginalList(List<String> pictureOriginalList) {
		this.pictureOriginalList = pictureOriginalList;
	}

	public String getDynamicUrl() {
		return dynamicUrl;
	}

	public void setDynamicUrl(String dynamicUrl) {
		this.dynamicUrl = dynamicUrl;
	}

	public Boolean getIsAuthOfSelf() {
		return isAuthOfSelf;
	}

	public void setIsAuthOfSelf(Boolean isAuthOfSelf) {
		this.isAuthOfSelf = isAuthOfSelf;
	}

	public Boolean getIsVipOfSelf() {
		return isVipOfSelf;
	}

	public void setIsVipOfSelf(Boolean isVipOfSelf) {
		this.isVipOfSelf = isVipOfSelf;
	}

	public List<UserAppointVO> getAppointList() {
		return appointList;
	}

	public void setAppointList(List<UserAppointVO> appointList) {
		this.appointList = appointList;
	}

	public Integer getShowVideoChat() {
		return showVideoChat;
	}

	public void setShowVideoChat(Integer showVideoChat) {
		this.showVideoChat = showVideoChat;
	}

	public Integer getBalanceEnough() {
		return balanceEnough;
	}

	public void setBalanceEnough(Integer balanceEnough) {
		this.balanceEnough = balanceEnough;
	}

	public Integer getAudioBalanceEnough() {
		return audioBalanceEnough;
	}

	public void setAudioBalanceEnough(Integer audioBalanceEnough) {
		this.audioBalanceEnough = audioBalanceEnough;
	}

	public int getOnlineStatue() {
		return onlineStatue;
	}

	public void setOnlineStatue(int onlineStatue) {
		this.onlineStatue = onlineStatue;
	}
	
}
