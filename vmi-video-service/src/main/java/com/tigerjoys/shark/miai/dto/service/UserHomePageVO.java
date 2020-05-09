package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;

import com.tigerjoys.shark.miai.agent.dto.AnchorDefendTopDto;

/**
 * 用户个人主页资料详情VO
 * @author lipeng
 *
 */
public class UserHomePageVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7019602663500150730L;

	//个人主页头部部分
	/**
	 * 用户主页详情
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * Ta的相册图片地址urllist 最多8个
	 */
	private List<String> pictureList;
	
	/**
	 * 亲密约头像图片地址urllist 展示3个
	 */
	private List<String> friendPhotoList;
	
	
	/**
	 * 关注状态 0 未关注 1关注
	 */
	private Integer isAttension;

	//个人主页详情部分
	/**
	 * 自评形象 2个
	 */
	private List<Object> selfReportedDesc;
	
	/**
	 * 用户印象 3个
	 */
	private List<ImpressionDto> userImpression;
	
	/**
	 * 个人资料
	 */
	private List<String> personalData;
	
	/**
	 * 用户评价数
	 */
	private Integer evaluateCount;
	
	/**
	 * 用户未评数
	 */
	private Integer noEvaluateCount;
	
	/**
	 * 用户评价list
	 */
	private List<UserEvaluationDto> userEvaluationList;
	
	/**
	 * 用户评价list
	 */
	private List<UserShortVideoDto> shortVideoList;
	
	/**
	 * 提审账号ID
	 */
	private Long toOnlyOneUserId;
	
	/**
	 * 礼物图标list 展示5个
	 */
	private List<String> giftIconList;
	
	/**
	 * 个性化图片list
	 */
	private List<String> individuationImgList;
	/**
     * 音频展示文字
     */
    private String audioText;
    /**
     * 音频地址
     */
    private String audioPath;
    
    /**
     * 举报  0不显示 1 显示
     */
    private int reportShow;
    
    /**
     * 守护图片，等级图片list
     */
    private List<AnchorDefendTopDto> guardData;
    /**
     * 是否是这个主播的守护 0 不是 1 是
     */
    private int guardStatus;
	
    /**
     * 年龄|距离
     */
    private String ageDistance;
    
	
	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<String> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	public List<String> getFriendPhotoList() {
		return friendPhotoList;
	}

	public void setFriendPhotoList(List<String> friendPhotoList) {
		this.friendPhotoList = friendPhotoList;
	}

	public List<Object> getSelfReportedDesc() {
		return selfReportedDesc;
	}

	public void setSelfReportedDesc(List<Object> selfReportedDesc) {
		this.selfReportedDesc = selfReportedDesc;
	}

	public List<ImpressionDto> getUserImpression() {
		return userImpression;
	}

	public void setUserImpression(List<ImpressionDto> userImpression) {
		this.userImpression = userImpression;
	}

	public Integer getIsAttension() {
		return isAttension;
	}

	public void setIsAttension(Integer isAttension) {
		this.isAttension = isAttension;
	}

	public List<String> getPersonalData() {
		return personalData;
	}

	public void setPersonalData(List<String> personalData) {
		this.personalData = personalData;
	}

	public Integer getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(Integer evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public Integer getNoEvaluateCount() {
		return noEvaluateCount;
	}

	public void setNoEvaluateCount(Integer noEvaluateCount) {
		this.noEvaluateCount = noEvaluateCount;
	}

	public List<UserEvaluationDto> getUserEvaluationList() {
		return userEvaluationList;
	}

	public void setUserEvaluationList(List<UserEvaluationDto> userEvaluationList) {
		this.userEvaluationList = userEvaluationList;
	}

	public Long getToOnlyOneUserId() {
		return toOnlyOneUserId;
	}

	public void setToOnlyOneUserId(Long toOnlyOneUserId) {
		this.toOnlyOneUserId = toOnlyOneUserId;
	}

	public List<UserShortVideoDto> getShortVideoList() {
		return shortVideoList;
	}

	public void setShortVideoList(List<UserShortVideoDto> shortVideoList) {
		this.shortVideoList = shortVideoList;
	}

	public List<String> getGiftIconList() {
		return giftIconList;
	}

	public void setGiftIconList(List<String> giftIconList) {
		this.giftIconList = giftIconList;
	}

	public List<String> getIndividuationImgList() {
		return individuationImgList;
	}

	public void setIndividuationImgList(List<String> individuationImgList) {
		this.individuationImgList = individuationImgList;
	}

	public String getAudioText() {
		return audioText;
	}

	public void setAudioText(String audioText) {
		this.audioText = audioText;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	public int getReportShow() {
		return reportShow;
	}

	public void setReportShow(int reportShow) {
		this.reportShow = reportShow;
	}

	public List<AnchorDefendTopDto> getGuardData() {
		return guardData;
	}

	public void setGuardData(List<AnchorDefendTopDto> guardData) {
		this.guardData = guardData;
	}

	public int getGuardStatus() {
		return guardStatus;
	}

	public void setGuardStatus(int guardStatus) {
		this.guardStatus = guardStatus;
	}

	public String getAgeDistance() {
		return ageDistance;
	}

	public void setAgeDistance(String ageDistance) {
		this.ageDistance = ageDistance;
	}

	
	
}
