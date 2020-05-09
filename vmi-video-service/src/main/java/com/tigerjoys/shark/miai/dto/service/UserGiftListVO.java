package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 主播礼物贡献榜
 * @author lipeng
 */
public class UserGiftListVO {
	
	/**
	 * 礼物List
	 */
	private List<UserGiftListDto> giftList;
	
	/**
	 * 礼物总数
	 */
	private String giftTotalCountStr;


	public List<UserGiftListDto> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<UserGiftListDto> giftList) {
		this.giftList = giftList;
	}

	public String getGiftTotalCountStr() {
		return giftTotalCountStr;
	}

	public void setGiftTotalCountStr(String giftTotalCountStr) {
		this.giftTotalCountStr = giftTotalCountStr;
	}

}
