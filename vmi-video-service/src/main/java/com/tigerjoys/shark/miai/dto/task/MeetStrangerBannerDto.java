package com.tigerjoys.shark.miai.dto.task;


/**
 * 遇见页面Banner展示Dto
 * @author 刘满
 *
 */
public class MeetStrangerBannerDto {
	
	/**
	 * 图片地址
	 */
	private String imageurl;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	private int opentype;
	
	/**
	 * android内页名
	 */
	private String androidPage;
	
	/**
	 * ios内页名
	 */
	private String iosPage;
	
	/**
	 * 打开链接地址
	 */
	private String openurl;
	
	/**
	 * 点击埋点事件
	 */
	private String clickEvent;
	
	/**
	 * 应用内链接需要的参数
	 */
	private String parame;
	
	/**
	 * 公告title
	 */
	private String title;

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getAndroidPage() {
		return androidPage;
	}

	public void setAndroidPage(String androidPage) {
		this.androidPage = androidPage;
	}

	public String getIosPage() {
		return iosPage;
	}

	public void setIosPage(String iosPage) {
		this.iosPage = iosPage;
	}

	public String getClickEvent() {
		return clickEvent;
	}

	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}

	public String getParame() {
		return parame;
	}

	public void setParame(String parame) {
		this.parame = parame;
	}

	public int getOpentype() {
		return opentype;
	}

	public void setOpentype(int opentype) {
		this.opentype = opentype;
	}

	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
