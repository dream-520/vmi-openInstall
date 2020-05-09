package com.tigerjoys.shark.miai.dto.task;

/**
 * 业务消息Dto
 * @author liuman
 *
 */
public class BusinessMessageViewDto {
	
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
	 * 消息简介
	 */
	private String content;
	
	/**
	 * 打开链接地址
	 */
	private String openurl;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	private int opentype;
	
	/**
	 * 点击类型:0-不可点击,1-可点击
	 */
	private int hitType;
	
	/**
	 * 安卓页面名称(对应BussinessMessageTypeEnum的androidAppPage)
	 */
	private String androidAppPage;
	
	/**
	 * ios页面名称(对应BussinessMessageTypeEnum的iosAppPage)
	 */
	private String iosAppPage;
	
	/**
	 * 跳转参数(类似于{"strValue1":"129884634"}这种格式)
	 */
	private String param;

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

	public int getHitType() {
		return hitType;
	}

	public void setHitType(int hitType) {
		this.hitType = hitType;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAndroidAppPage() {
		return androidAppPage;
	}

	public void setAndroidAppPage(String androidAppPage) {
		this.androidAppPage = androidAppPage;
	}

	public String getIosAppPage() {
		return iosAppPage;
	}

	public void setIosAppPage(String iosAppPage) {
		this.iosAppPage = iosAppPage;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
