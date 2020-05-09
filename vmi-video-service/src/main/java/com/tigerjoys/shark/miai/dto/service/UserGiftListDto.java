package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 用户亲密榜dto
 * @author lipeng
 */
public class UserGiftListDto {
	
	
	/**
	 * 用户主页详情
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * 礼物贡献
	 */
	private String giftContribution;
	
	/**
	 * 礼物数量
	 */
	private String giftCount;
	
	/**
	 * 礼物图片list
	 */
	private List<String> imgList;

	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getGiftContribution() {
		return giftContribution;
	}

	public void setGiftContribution(String giftContribution) {
		this.giftContribution = giftContribution;
	}

	public String getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(String giftCount) {
		this.giftCount = giftCount;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

}
