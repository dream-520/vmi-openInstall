package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户相册
 * @author chengang
 *
 */
public class UserPhotoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -998640411653384694L;
	
	/**
	 * 相册ID
	 */
	private String photoId;
	
	/**
	 * 图片路径
	 */
	private String path;
	
	/**
	 * 模糊图片
	 */
	private String obscurePath;
	
	/**
	 * 相册是否是私密,0否,1是
	 */
	private int obscure;
	
	/**
	 * 是否已经购买
	 */
	private int buy;
	
	/**
	 * 购买私密视频的费用
	 */
	private int costDiamond;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getObscurePath() {
		return obscurePath;
	}

	public void setObscurePath(String obscurePath) {
		this.obscurePath = obscurePath;
	}

	public int getCostDiamond() {
		return costDiamond;
	}

	public void setCostDiamond(int costDiamond) {
		this.costDiamond = costDiamond;
	}
	
}
