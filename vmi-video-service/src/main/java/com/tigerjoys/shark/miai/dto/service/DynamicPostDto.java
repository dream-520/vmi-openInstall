package com.tigerjoys.shark.miai.dto.service;

/**
 * 客户端上传动态时配置参数实体数据
 * @author shiming
 */
public class DynamicPostDto {

	/**
	 * 上传动态内容
	 */
	private String content;
	
	/**
	 * 上传动态类型
	 */
	private Integer type;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
