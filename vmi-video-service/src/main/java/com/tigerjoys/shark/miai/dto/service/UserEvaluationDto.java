package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 用户评价标签dto
 * @author lipeng
 *
 */
public class UserEvaluationDto {
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 评价内容
	 */
	private String evaluationText;
	
	/**
	 * 用户印象 
	 */
	private List<Object> userImpression;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Object> getUserImpression() {
		return userImpression;
	}

	public void setUserImpression(List<Object> userImpression) {
		this.userImpression = userImpression;
	}

	public String getEvaluationText() {
		return evaluationText;
	}

	public void setEvaluationText(String evaluationText) {
		this.evaluationText = evaluationText;
	}

}
