package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/*
 * 推荐对应的主播数据
 */
public class HotAnchorDto {

	private String name;
	
	private String color;
	
	private List<AnchorListVO> anchors;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<AnchorListVO> getAnchors() {
		return anchors;
	}
	public void setAnchors(List<AnchorListVO> anchors) {
		this.anchors = anchors;
	}
	
}
