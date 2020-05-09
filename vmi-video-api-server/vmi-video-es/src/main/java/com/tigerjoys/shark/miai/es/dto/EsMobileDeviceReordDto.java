
package com.tigerjoys.shark.miai.es.dto;

import java.util.Date;

public class EsMobileDeviceReordDto {
	private String clientid;
	private String appName;
	private Date createDate;
	
	public static EsMobileDeviceReordDto preDto(String clientid,String appName,Date createDate){
		EsMobileDeviceReordDto dto = new EsMobileDeviceReordDto();
		dto.setClientid(clientid);
		dto.setAppName(appName);
		dto.setCreateDate(createDate);
		return dto;
	}
	
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
