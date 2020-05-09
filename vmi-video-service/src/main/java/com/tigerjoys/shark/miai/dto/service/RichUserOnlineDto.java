package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

public class RichUserOnlineDto {
	
	/**
	 * 提示文字
	 */
	private int tabType;
	
	/**
	 * 列表
	 */
	private List<UserBaseInfo> dataList;

	/**
	 * 提示文字
	 */
	private String info;
	
	

	public List<UserBaseInfo> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserBaseInfo> dataList) {
		this.dataList = dataList;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getTabType() {
		return tabType;
	}

	public void setTabType(int tabType) {
		this.tabType = tabType;
	}

}
