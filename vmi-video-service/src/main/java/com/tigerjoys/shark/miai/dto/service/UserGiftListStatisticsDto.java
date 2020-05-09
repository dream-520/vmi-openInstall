package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户贡献榜组合数据dto
 * @author lipeng
 */
public class UserGiftListStatisticsDto {
	
	/**
	 * 用户ID
	 */
	private Long userid;	
	
	/**
	 * 主播ID
	 */
	private Long anchorid;
	
	/**
	 * 礼物数量
	 */
	private Integer gift_count;
	
	/**
	 * 礼物贡献
	 */
	private Integer gift_contribution;
	
	/**
	 * 数据类型 1 真 2假
	 */
	private Integer type;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}

	public Integer getGift_count() {
		return gift_count;
	}

	public void setGift_count(Integer gift_count) {
		this.gift_count = gift_count;
	}

	public Integer getGift_contribution() {
		return gift_contribution;
	}

	public void setGift_contribution(Integer gift_contribution) {
		this.gift_contribution = gift_contribution;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
