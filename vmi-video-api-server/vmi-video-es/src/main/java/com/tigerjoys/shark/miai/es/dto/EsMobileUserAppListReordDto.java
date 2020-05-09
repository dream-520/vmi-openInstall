
package com.tigerjoys.shark.miai.es.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EsMobileUserAppListReordDto {
	private long userId;
	private int userType;
	private List<String> appList;
	private List<String> curAppList;
	private LocalDateTime createTime;
	
	
	
	
	public static EsMobileUserAppListReordDto preDto( long userId,int userType,List<String> appList,List<String> curAppList, LocalDateTime createTime){
		EsMobileUserAppListReordDto dto = new EsMobileUserAppListReordDto();
		dto.setUserId(userId);
		dto.setUserType(userType);
		dto.setAppList(appList);
		dto.setCurAppList(curAppList);
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



	public List<String> getAppList() {
		return appList;
	}



	public void setAppList(List<String> appList) {
		this.appList = appList;
	}



	public List<String> getCurAppList() {
		return curAppList;
	}



	public void setCurAppList(List<String> curAppList) {
		this.curAppList = curAppList;
	}



	public LocalDateTime getCreateTime() {
		return createTime;
	}



	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	

}
