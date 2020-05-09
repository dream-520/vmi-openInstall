package com.tigerjoys.shark.miai.dto.service;

import java.util.List;


public class AhchorShortVideoDto {
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 视频列表
	 */
	private List<AnchorVideoWatchDto> anchorList;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<AnchorVideoWatchDto> getAnchorList() {
		return anchorList;
	}

	public void setAnchorList(List<AnchorVideoWatchDto> anchorList) {
		this.anchorList = anchorList;
	}


}
