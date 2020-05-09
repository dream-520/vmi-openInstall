
package com.tigerjoys.shark.miai.es.dto;

import java.time.LocalDateTime;

public class EsMobileAppListReordDto {
	private long userId;
	private int userType;
	private String appName;
	private LocalDateTime createTime;
	
	public static EsMobileAppListReordDto preDto( long userId,int userType,String appName,LocalDateTime createTime){
		EsMobileAppListReordDto dto = new EsMobileAppListReordDto();
		dto.setUserId(userId);
		dto.setUserType(userType);
		dto.setAppName(appName);
		//dto.setCreateTime(Tools.getFormatDate(createTime, "yyyy-MM-dd HH:mm:ss"));
		dto.setCreateTime(createTime);
		return dto;
	}
	
	

	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public int getUserType() {
		return userType;
	}



	public void setUserType(int userType) {
		this.userType = userType;
	}


	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}



	public LocalDateTime getCreateTime() {
		return createTime;
	}



	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}



	

}
