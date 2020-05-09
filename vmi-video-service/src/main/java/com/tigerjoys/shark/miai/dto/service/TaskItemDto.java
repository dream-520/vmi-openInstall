package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户资料详情dto
 * @author lipeng
 *
 */
public class TaskItemDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6678783839985303519L;
	/**
     * 任务标题
     */
    private String title;
    /**
     * 任务奖励
     */
    private String content;
    /**
     * 任务状态 1完成 0未完成
     */
    private int status;
    /**
     * app内页名
     */
    private String androidPage;
    /**
     * app内页名
     */
    private String iosPage;
    /**
     * 应用内链接需要的参数
     */
    private String parame;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAndroidPage() {
		return androidPage;
	}
	public void setAndroidPage(String androidPage) {
		this.androidPage = androidPage;
	}
	public String getParame() {
		return parame;
	}
	public void setParame(String parame) {
		this.parame = parame;
	}
	public String getIosPage() {
		return iosPage;
	}
	public void setIosPage(String iosPage) {
		this.iosPage = iosPage;
	}
}
