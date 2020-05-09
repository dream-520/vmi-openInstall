package com.tigerjoys.shark.miai.dto.service;

public class RechargePriceDto {
	
	private Long priceId;
	
	private Integer diamond;
	
	private Integer money;
	
	private String buyText;

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getBuyText() {
		return buyText;
	}

	public void setBuyText(String buyText) {
		this.buyText = buyText;
	}
	
	
}
