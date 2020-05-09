package com.tigerjoys.shark.miai.dto.service;

public class HomeTabTypeDto {
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 是否有banner
	 */
	private boolean showBanner;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isShowBanner() {
		return showBanner;
	}
	public void setShowBanner(boolean showBanner) {
		this.showBanner = showBanner;
	}
	
	
}
