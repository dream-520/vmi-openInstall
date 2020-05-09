package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;
import java.util.List;

/**
 * 评价DTO
 * @author 刘满
 *
 */
public class ComplaintCreateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919680764079226306L;
	
	/**
	 * 投诉内容
	 */
	private String content;
	
	/**
	 * 举报类型
	 */
	private int reportType;
	
	/**
	 * 举报图片集合
	 */
	private List<String> picList;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

}
