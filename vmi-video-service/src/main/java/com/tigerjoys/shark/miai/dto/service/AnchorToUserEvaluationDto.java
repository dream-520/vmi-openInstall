package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;

/**
 * 主播对用户的评价格
 * @author yangjunming
 *
 */
public class AnchorToUserEvaluationDto {
	/**
	 * 评价ID
	 */
	private long type;
	
	/**
	 * 评价信息
	 */
	private String text;
	
	
	
	public static AnchorToUserEvaluationDto preDto(AppLabelEntity entity){
		AnchorToUserEvaluationDto dto = new AnchorToUserEvaluationDto();
		dto.setType(entity.getId());
		dto.setText(entity.getName());
		return dto;
	}



	public long getType() {
		return type;
	}



	public void setType(long type) {
		this.type = type;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}

	
	
	
}
