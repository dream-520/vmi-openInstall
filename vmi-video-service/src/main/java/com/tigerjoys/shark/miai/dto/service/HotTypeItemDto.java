package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

import org.shark.miai.common.dto.IdItemDto;

/**
 * 技能一级条目 DTO
 * @author shiming
 *
 */
public class HotTypeItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8494183036523016718L;

	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 图片
	 */
	private String picUrl;
	

	
	public static HotTypeItemDto preDto(IdItemDto item){
		HotTypeItemDto dto = new HotTypeItemDto();
		dto.setId(item.getId());
		dto.setName(item.getName());
		dto.setPicUrl(item.getBigPicUrl());
		return dto;
	}
	
	public static HotTypeItemDto preDto(int id,String name,String picUrl){
		HotTypeItemDto dto = new HotTypeItemDto();
		dto.setId(id);
		dto.setName(name);
		dto.setPicUrl(picUrl);
		return dto;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


}
