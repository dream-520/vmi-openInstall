package com.tigerjoys.shark.miai.dto.service;

public class AnchorStarPrizeDto {

	/**
	 * 期号
	 */
	private String issue;
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 是否中奖标题
	 */
	private String prizeTitle;
	
	/**
	 * 是否中奖显示内容
	 */
	private String prizeText;

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

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

	public String getPrizeTitle() {
		return prizeTitle;
	}

	public void setPrizeTitle(String prizeTitle) {
		this.prizeTitle = prizeTitle;
	}

	public String getPrizeText() {
		return prizeText;
	}

	public void setPrizeText(String prizeText) {
		this.prizeText = prizeText;
	}
	
}
