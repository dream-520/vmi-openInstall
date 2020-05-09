package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户亲密榜dto
 * @author lipeng
 */
public class UserCloseDto {
	
	
	/**
	 * 用户主页详情
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * 亲密值
	 */
	private Integer closeValue;
	
	/**
	 * 在线状态
	 */
	private String stateStr;

	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getCloseValue() {
		return closeValue;
	}

	public void setCloseValue(Integer closeValue) {
		this.closeValue = closeValue;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

}
