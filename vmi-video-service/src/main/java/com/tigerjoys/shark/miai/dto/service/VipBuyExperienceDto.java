package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * 购买VIP体验Dao
 * @author chengang
 *
 */
public class VipBuyExperienceDto {
	
	/**
	 * 价格ID
	 */
	private long priceId;
	
	/**
	 * 价格
	 */
	private long money;
	
	/**
	 * 天数
	 */
	private long days;
	
	/**
	 * 
	 * @param priceId  	价格ID
	 * @param money 	价格(元)
	 * @param days		天数
	 * @return
	 */
	public static VipBuyExperienceDto preDto(long priceId,long money,long days){
		VipBuyExperienceDto dto= new VipBuyExperienceDto();
		dto.setPriceId(priceId);;
		dto.setMoney(money);
		dto.setDays(days);
		return dto;
	}


	public long getMoney() {
		return money;
	}


	public void setMoney(long money) {
		this.money = money;
	}


	public long getDays() {
		return days;
	}


	public void setDays(long days) {
		this.days = days;
	}


	public long getPriceId() {
		return priceId;
	}


	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}

	
}
