package com.tigerjoys.shark.miai.dto.service;

/**
 * 派单类别
 * 
 * @author yangjunming
 *
 */
public class DispatchHeadDto {
	/**
	 * 图标路径
	 */
	private String picUrl;

	/**
	 * 名字
	 */
	private String name;

	/**
	 * id
	 */
	private int id;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
