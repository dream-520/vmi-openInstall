package com.tigerjoys.shark.miai.dto.task;

import java.util.Date;

/**
 * 系统消息Dto
 * @author liuman
 *
 */
public class MessageViewDto {
	
	/**
	 * 消息ID
	 */
	private long message;
	
	/**
	 * 消息创建时间
	 */
	private String createDate;
	
	/**
	 * 消息创建时间
	 */
	private long createTime;
	
	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 简介
	 */
	private String intro;
	
	/**
	 * 打开链接地址
	 */
	private String openurl;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	private int opentype;
	
	/**
	 * 消息类型,0系统公告,1系统升级,2活动消息
	 */
	private int type;

	public long getMessage() {
		return message;
	}

	public void setMessage(long message) {
		this.message = message;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}

	public int getOpentype() {
		return opentype;
	}

	public void setOpentype(int opentype) {
		this.opentype = opentype;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
