package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.inter.entity.TopHeadInfoEntity;

/**
 * 头条数据
 * 
 * @author yangjunming
 *
 */
public class TopLineHeadDto {
	/**
	 * 名字 可为空
	 */
	private String name;

	/**
	 * 内容
	 */
	private String info;

	public static TopLineHeadDto preDto(TopHeadInfoEntity entity){
		TopLineHeadDto dto = new TopLineHeadDto();
		dto.setName(Tools.isNull(entity.getName())?"":entity.getName());
		dto.setInfo(Tools.isNull(entity.getInfo())?"":entity.getInfo());
		return dto;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
