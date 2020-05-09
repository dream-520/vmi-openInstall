package com.tigerjoys.shark.miai.dto.service;

/**
 * 推荐达人数据
 * 
 * @author yangjunming
 *
 */
public class RecommendHeadDto {
	/**
	 * 达人图片地址
	 */
	private String picUrl;

	/**
	 * 达人id
	 */
	private long id;

	/**
	 * 是否有形象视频 0没有 1有
	 */
	private int videoState;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVideoState() {
		return videoState;
	}

	public void setVideoState(int videoState) {
		this.videoState = videoState;
	}
}
