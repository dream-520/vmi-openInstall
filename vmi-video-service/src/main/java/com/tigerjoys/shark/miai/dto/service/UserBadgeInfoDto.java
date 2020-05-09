package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户徽章信息dto
 * @author liuman
 *
 */
public class UserBadgeInfoDto implements Serializable {

	private static final long serialVersionUID = 3680854050588070858L;
	
	/**
     * 徽章级别
     */
    //private int badgeLevel;
	
	/**
	 * 徽章名称
	 */
	private String badgeName;
	
	/**
	 * 徽章图标
	 */
	private String badgePicture;
	
	/**
	 * 剩余天数
	 */
	private String remindDays;



	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public String getBadgePicture() {
		return badgePicture;
	}

	public void setBadgePicture(String badgePicture) {
		this.badgePicture = badgePicture;
	}

	public String getRemindDays() {
		return remindDays;
	}

	public void setRemindDays(String remindDays) {
		this.remindDays = remindDays;
	}
	

}
