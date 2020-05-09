package com.tigerjoys.shark.miai.dto.service;

import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;

/**
 * 代理分类分成明细统计dto
 * 
 * @author yangjunming
 *
 */
public class UserDividedMappingDto {

	/**
	 * id
	 */
	private long id;
	/**
	 * 分类名称
	 */
	private String mappingName;

	/**
	 * 结算单数
	 */
	private Integer tradeNum;

	/**
	 * 累计分成
	 */
	private String dividedAmount;
	


	public static UserDividedMappingDto pareDto(UserInviteMappingEntity entity) {
		UserDividedMappingDto dto = new UserDividedMappingDto();
		dto.setId(entity.getId());
		dto.setMappingName(entity.getName());
		dto.setTradeNum(entity.getTrade_num());
		dto.setDividedAmount("" + (entity.getTrade_amount() / 100.0));
		return dto;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getMappingName() {
		return mappingName;
	}



	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}



	public Integer getTradeNum() {
		return tradeNum;
	}



	public void setTradeNum(Integer tradeNum) {
		this.tradeNum = tradeNum;
	}



	public String getDividedAmount() {
		return dividedAmount;
	}



	public void setDividedAmount(String dividedAmount) {
		this.dividedAmount = dividedAmount;
	}

	

}
