
package com.tigerjoys.shark.miai.es.dto;

import java.util.Date;

import org.shark.miai.common.enums.UserTypeEnum;

import com.tigerjoys.shark.miai.es.enums.EsUserOnlineUserEventEnum;

public class EsUserOnlineReordDto {
	private Long userId;
	private Integer userType;
	private Integer userEvent;
	private Date createDate;
	
	public static EsUserOnlineReordDto preDto(Long userId,UserTypeEnum userType,EsUserOnlineUserEventEnum userEvent,Date createDate){
		EsUserOnlineReordDto dto = new EsUserOnlineReordDto();
		dto.setUserId(userId);
		dto.setUserType(userType.getCode());
		dto.setUserEvent(userEvent.getCode());
		dto.setCreateDate(createDate);
		return dto;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(Integer userEvent) {
		this.userEvent = userEvent;
	}
	

}
