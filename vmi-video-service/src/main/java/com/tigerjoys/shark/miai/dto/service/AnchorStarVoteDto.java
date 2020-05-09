package com.tigerjoys.shark.miai.dto.service;

public class AnchorStarVoteDto {

	/**
	 * 投注期数
	 */
	private int issue;
	/**
	 * 小红花数量
	 */
	private long flowers;
	
	/**
	 * 投注的用户
	 */
	private long userid;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 防止重复投注的交易号
	 */
	String transactionFlow;

	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}

	public long getFlowers() {
		return flowers;
	}

	public void setFlowers(long flowers) {
		this.flowers = flowers;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
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

	public String getTransactionFlow() {
		return transactionFlow;
	}

	public void setTransactionFlow(String transactionFlow) {
		this.transactionFlow = transactionFlow;
	}
	
}
