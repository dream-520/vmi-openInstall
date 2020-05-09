package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户视频
 * @author chengang
 *
 */
public class UserVideoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4095688318156403975L;
	
	/**
	 * 视频ID
	 */
	private String videoId;
	
	/**
	 * 视频封面原图
	 */
	private String videoPhoto;
	
	/**
	 * 模糊封面地址
	 */
	private String obscurePath;
	
	/**
	 * 视频地址
	 */
	private String videoPath;
	
	/**
	 * 是否是私密视频,0否,1是
	 */
	private int obscure;
	
	/**
	 * 如果是私密视频，是否已购买,0否,1是
	 */
	private int buy;
	
	/**
	 * 购买私密视频的费用
	 */
	private int costDiamond;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoPhoto() {
		return videoPhoto;
	}

	public void setVideoPhoto(String videoPhoto) {
		this.videoPhoto = videoPhoto;
	}

	public String getObscurePath() {
		return obscurePath;
	}

	public void setObscurePath(String obscurePath) {
		this.obscurePath = obscurePath;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public int getObscure() {
		return obscure;
	}

	public void setObscure(int obscure) {
		this.obscure = obscure;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public int getCostDiamond() {
		return costDiamond;
	}

	public void setCostDiamond(int costDiamond) {
		this.costDiamond = costDiamond;
	}

}
