package org.shark.miai.common.dto;

import java.io.Serializable;

/**
 * 技能一级条目 DTO
 * @author shiming
 *
 */
public class UpYunVideoTransQueryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8494183036523016718L;

	/**
	 * 任务ID
	 */
	private String task_id;
	
	/**
	 * 结果状态
	 */
	private Integer status_code;
	
	/**
	 * 服务名
	 */
	private String service;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 签名
	 */
	private String signature;
	/*
	public static UpYunVideoTransQueryDto preDto(int id,String name,String ico,String bigPicUrl){
		UpYunVideoTransQueryDto dto = new UpYunVideoTransQueryDto();
		dto.setId(id);
		dto.setName(name);
		dto.setPicUrl(ico);
		dto.setBigPicUrl(bigPicUrl);
		return dto;
	}
*/

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public Integer getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
	
	
}
