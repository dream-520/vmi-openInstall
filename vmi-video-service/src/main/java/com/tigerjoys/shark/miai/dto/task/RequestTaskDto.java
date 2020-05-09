package com.tigerjoys.shark.miai.dto.task;

import java.io.Serializable;

import com.tigerjoys.nbs.web.context.RequestHeader;


public class RequestTaskDto extends RequestHeader implements Serializable {

	private static final long serialVersionUID = 253213003587638275L;
	
	/**
	 * App请求任务当前页
	 */
	private int currentPage;
	
	/**
	 * App请求任务每页展示的页数
	 */
	private int pageSize;
	
	/**
	 * App请求任务分页戳
	 */
	private String stamp;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

}
