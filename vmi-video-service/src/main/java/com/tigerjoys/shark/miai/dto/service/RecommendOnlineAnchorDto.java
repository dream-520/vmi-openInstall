package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 推荐在线主播
 * @author yangjunming
 *
 */
public class RecommendOnlineAnchorDto {
	
	
	
	/**
	 * 显示信息
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * 0不显示关注按钮 1显示关注按钮
	 */
	private Integer attention;


	public Integer getAttention() {
		return attention;
	}

	public void setAttention(Integer attention) {
		this.attention = attention;
	}

	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
