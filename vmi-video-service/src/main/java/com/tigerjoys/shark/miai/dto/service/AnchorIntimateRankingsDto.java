package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户亲密榜数据dto
 * @author lipeng
 */
public class AnchorIntimateRankingsDto {
	
	
	/**
	 * 普通用户ID
	 */
	private Long userid;
	
	/**
	 * 总收入
	 */
	private Integer total_money;

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

	public Integer getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Integer total_money) {
		this.total_money = total_money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
