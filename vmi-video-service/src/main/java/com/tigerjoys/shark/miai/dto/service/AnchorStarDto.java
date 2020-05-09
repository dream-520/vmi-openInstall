package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

public class AnchorStarDto {

	private int issue;
	private long surplus;
	private long flowers;
	private boolean prize;
	/**
	 * 是否中奖标题
	 */
	private String prizeTitle;
	private String prizeText;
	
	private List<AnchorStarItem> items;
	
	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}

	public long getSurplus() {
		return surplus;
	}
	
	public void setSurplus(long surplus) {
		this.surplus = surplus;
	}
	
	public long getFlowers() {
		return flowers;
	}
	
	public void setFlowers(long flowers) {
		this.flowers = flowers;
	}
	
	public boolean isPrize() {
		return prize;
	}

	public void setPrize(boolean prize) {
		this.prize = prize;
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

	public List<AnchorStarItem> getItems() {
		return items;
	}

	public void setItems(List<AnchorStarItem> items) {
		this.items = items;
	}
	
}
