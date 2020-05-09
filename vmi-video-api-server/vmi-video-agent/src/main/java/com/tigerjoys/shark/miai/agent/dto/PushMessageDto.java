package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 推送消息dto
 * 
 * @author liuman
 *
 */
public class PushMessageDto implements Serializable {
	
	private static final long serialVersionUID = 4097549467988735982L;

	/**
	 * 推送消息类型，具体看@com.tigerjoys.shark.miai.agent.enums.PushTypeEnum
	 */
	private int msgType;
	
	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 消息内容
	 */
	private String content;
	
	/**
	 * android app tag
	 */
	private String appTag;
	
	/**
	 * 已经没用了
	 */
	private String url;
	
	/**
	 * 用户ID
	 */
	private int user; // 用户id
	
	/**
	 * 通知栏是否展示
	 */
	private int showNotif;//0是显示  1是不显示
	
	/**
	 * android可以发送多个消息不被覆盖
	 */
	private int notifyId;
	
	/**
	 * 参数json字符串
	 */
	private String param;
	
	/**
	 * app包名
	 */
	private String packageName;
	
	/**
	 * ios控制信息   区分前后端  默认为0  标识前后端都进行相应
	 */
	private int ios_control;
	
	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

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

	public String getAppTag() {
		return appTag;
	}

	public void setAppTag(String appTag) {
		this.appTag = appTag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}


	public int getShowNotif() {
		return showNotif;
	}

	public void setShowNotif(int showNotif) {
		this.showNotif = showNotif;
	}

	public int getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(int notifyId) {
		this.notifyId = notifyId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getIos_control() {
		return ios_control;
	}

	public void setIos_control(int ios_control) {
		this.ios_control = ios_control;
	}
	
}
