package com.tigerjoys.shark.miai.dto.service;

/**
 * 短视频详情
 * @author yangjunming
 *
 */
public class VideoDescDto {
	
	/**
	 * 短视频ID
	 */
	private Long videoId;
	
	/**
	 * 用户信息
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * 视频点赞数
	 */
	private String videoPraise;
	
	/**
	 * 视频点赞数
	 */
	private Boolean praiseFlag;
	
	/**
	 * 是否关注
	 */
	private Boolean attentionFlag;
	
	/**
	 * 观看数
	 */
	private String watchNum;
	
	/**
	 * 视频封面
	 */
	private String videoPhoto;
	
	/**
	 * 视频地址
	 */
	private String videoPath;

	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getVideoPraise() {
		return videoPraise;
	}

	public void setVideoPraise(String videoPraise) {
		this.videoPraise = videoPraise;
	}

	public String getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(String watchNum) {
		this.watchNum = watchNum;
	}

	public String getVideoPhoto() {
		return videoPhoto;
	}

	public void setVideoPhoto(String videoPhoto) {
		this.videoPhoto = videoPhoto;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public Boolean getPraiseFlag() {
		return praiseFlag;
	}

	public void setPraiseFlag(Boolean praiseFlag) {
		this.praiseFlag = praiseFlag;
	}

	public Boolean getAttentionFlag() {
		return attentionFlag;
	}

	public void setAttentionFlag(Boolean attentionFlag) {
		this.attentionFlag = attentionFlag;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	
}
