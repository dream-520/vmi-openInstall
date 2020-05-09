package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户举报详情dto
 * @author lipeng
 *
 */
public class UserInformDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705859879981228412L;

	/**
	 * 被举报用户ID
	 */
	private Long informid;
	
	/**
	 * 举报类型
	 */
	private Integer informType;
	
	/**
	 * 举报内容
	 */
	private String informInfo;

	public Long getInformid() {
		return informid;
	}

	public void setInformid(Long informid) {
		this.informid = informid;
	}

	public Integer getInformType() {
		return informType;
	}

	public void setInformType(Integer informType) {
		this.informType = informType;
	}

	public String getInformInfo() {
		return informInfo;
	}

	public void setInformInfo(String informInfo) {
		this.informInfo = informInfo;
	}
	
}
