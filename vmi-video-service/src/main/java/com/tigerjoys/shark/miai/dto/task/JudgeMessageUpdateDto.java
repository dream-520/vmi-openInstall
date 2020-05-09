package com.tigerjoys.shark.miai.dto.task;

/**
 * 消息模块小红点单体消息数据传输类
 * 
 * @author liuman
 *
 */
public class JudgeMessageUpdateDto {

	/**
	 * 消息类型
	 */
	private int msgType;
	/**
	 * 消息更新标志: 0-无新的消息,1-有新的消息
	 */
	private long msgTag;
	/**
	 * 标题 （通知栏/系统公告/系统通知）
	 */
	private String title;
	/**
	 * 内容 （通知栏/系统公告/系统通知）
	 */
	private String info;
	/**
	 * 要打开的app内页的名字（通知栏用）
	 */
	private String appName;
	/**
	 * 要打开的app内页的参数 json（通知栏用）
	 */
	private String param;

	/**
	 * 消息创建时间
	 */
	private long createDate;

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public long getMsgTag() {
		return msgTag;
	}

	public void setMsgTag(long msgTag) {
		this.msgTag = msgTag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
