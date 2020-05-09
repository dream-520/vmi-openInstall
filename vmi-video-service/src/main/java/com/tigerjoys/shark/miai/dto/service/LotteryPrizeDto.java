package com.tigerjoys.shark.miai.dto.service;

/**
 * 大转盘假中奖数据实体
 * @author shiming
 *
 */
public class LotteryPrizeDto {

	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 是否中奖显示内容
	 */
	private String prizeText;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPrizeText() {
		return prizeText;
	}

	public void setPrizeText(String prizeText) {
		this.prizeText = prizeText;
	}
	
}
