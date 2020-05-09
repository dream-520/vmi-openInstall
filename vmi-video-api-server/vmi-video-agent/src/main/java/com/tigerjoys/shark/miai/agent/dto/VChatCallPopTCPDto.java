package com.tigerjoys.shark.miai.agent.dto;

/**
 * 呼叫弹窗TCP通知
 * @author yangjunming
 *
 */
public class VChatCallPopTCPDto {
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像地址
	 */
	private String photo;
	
	/**
	 * 场景ID
	 */
	private int index;
	/**
	 * 视频地址
	 */
	private String videoUrl;

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	

	
}
