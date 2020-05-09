package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 充值推送消息实体
 * @author shiming
 *
 */
public class PushRichUserDto implements Serializable {
	
	private UserBaseInfo otherUserData;
	private String info;
	
	public UserBaseInfo getOtherUserData() {
		return otherUserData;
	}
	public void setOtherUserData(UserBaseInfo otherUserData) {
		this.otherUserData = otherUserData;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
