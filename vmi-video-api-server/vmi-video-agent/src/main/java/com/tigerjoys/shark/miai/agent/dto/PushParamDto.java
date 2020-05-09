package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;

/**
 * 推送消息参数dto
 * 
 * @author liuman
 *
 */
public class PushParamDto implements Serializable {
	
	private static final long serialVersionUID = -1932048572528409103L;

	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 消息内容
	 */
	private String content;
	
	/**
	 * 手机在小米注册的id
	 */
	private String clientId;
	
	/**
	 * 手机平台类型 ：0-未知;1-安卓;2-IOS
	 */
	private int platformType;
	
	/**
	 * 推送业务内容跳转参数
	 */
	private NewPushAppTagEnum pushAppTagEnum;
	
	/**
	 * 消息是否需要跳转 调用 PushTypeEnum
	 */
	private int msgType;
	
	/**
	 * 跳转到H5页面
	 */
	private String url;
	
	/**
	 * 消息是否需要跳转 调用 PushContentTypeEnum
	 */
	private int contentType;
	
	/**
	 * 用户id
	 */
	private long userId;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 用户头像
	 */
	private String userHead;
	
	/**
	 * 0-展示H5页面标题,1-不展示H5页面标题
	 */
	private int showH5TitleFalg;
	
	/**
	 * H5页面title
	 */
	private String H5Title;
	
	/**
	 * app包名
	 */
	private String packageName;
	
	/**
	 * 扩展信息  方便 根据类型设置参数数据
	 */
	private String extend;
	
	/**
	 * ios控制信息   区分前后端  默认为0  标识前后端都进行相应
	 */
	private int ios_control;
	
	private int pushChannel;
	
	private String huaweiToken;
	
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getPlatformType() {
		return platformType;
	}

	public void setPlatformType(int platformType) {
		this.platformType = platformType;
	}

	public NewPushAppTagEnum getPushAppTagEnum() {
		return pushAppTagEnum;
	}

	public void setPushAppTagEnum(NewPushAppTagEnum pushAppTagEnum) {
		this.pushAppTagEnum = pushAppTagEnum;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

	public int getShowH5TitleFalg() {
		return showH5TitleFalg;
	}

	public void setShowH5TitleFalg(int showH5TitleFalg) {
		this.showH5TitleFalg = showH5TitleFalg;
	}

	public String getH5Title() {
		return H5Title;
	}

	public void setH5Title(String h5Title) {
		H5Title = h5Title;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public int getIos_control() {
		return ios_control;
	}

	public void setIos_control(int ios_control) {
		this.ios_control = ios_control;
	}

	public int getPushChannel() {
		return pushChannel;
	}

	public void setPushChannel(int pushChannel) {
		this.pushChannel = pushChannel;
	}

	public String getHuaweiToken() {
		return huaweiToken;
	}

	public void setHuaweiToken(String huaweiToken) {
		this.huaweiToken = huaweiToken;
	}
	
}
