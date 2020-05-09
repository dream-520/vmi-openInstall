package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户评价标签dto
 * @author lipeng
 *
 */
public class UserShortVideoDto {
	
	/**
	 * 视频头像
	 */
	private String videoPhoto;
	
	/**
	 * 视频地址
	 */
	private String videoPath;

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

}
