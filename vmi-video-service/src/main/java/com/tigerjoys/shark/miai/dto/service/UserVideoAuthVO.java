package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户认证视频信息
 * @author lipeng
 *
 */
public class UserVideoAuthVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4387846154401469153L;

	/**
	 * 视频地址
	 */
	private String videoAuth;
	
	/**
	 * 视频是否认证0认证中 1成功 2失败或从未认证
	 */
	private Integer videoAuthState;
	
	/**
	 * 视频图片地址
	 */
	private String videoPicture;

	public String getVideoAuth() {
		return videoAuth;
	}

	public void setVideoAuth(String videoAuth) {
		this.videoAuth = videoAuth;
	}

	public Integer getVideoAuthState() {
		return videoAuthState;
	}

	public void setVideoAuthState(Integer videoAuthState) {
		this.videoAuthState = videoAuthState;
	}

	public String getVideoPicture() {
		return videoPicture;
	}

	public void setVideoPicture(String videoPicture) {
		this.videoPicture = videoPicture;
	}
	
}
