package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;

/**
 * 评价列表DTO
 * @author yangjunming
 *
 */
public class EvaluationDto {
	/**
	 * 评价ID
	 */
	private long id;
	
	/**
	 * 评价信息
	 */
	private String text;
	
	/**
	 * 背景色
	 */
	private String bgColor;
	
	public static EvaluationDto preDto(AppLabelEntity entity){
		EvaluationDto dto = new EvaluationDto();
		dto.setId(entity.getId());
		dto.setText(entity.getName());
		dto.setBgColor(entity.getColor());
		return dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	
	
	
}
