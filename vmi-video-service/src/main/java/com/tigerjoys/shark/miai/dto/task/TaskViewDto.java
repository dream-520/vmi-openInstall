package com.tigerjoys.shark.miai.dto.task;

import java.io.Serializable;

/**
 * 任务展示Dto
 * @author liuman
 *
 */
public class TaskViewDto implements Serializable{
	
	private static final long serialVersionUID = -4453075933326862902L;

	/**
	 * 任务ID
	 */
	private long id;
	
	/**
	 * 任务名称
	 */
	private String name;
	
	/**
	 * 任务ICON图片地址
	 */
	private String iconUrl;
	
	/**
	 * 任务描述文字
	 */
	private String text;
	
	/**
	 * 任务按钮文字
	 */
	private String btnText;
	
	/**
	 * 任务类型 11新手任务 ，12每日任务
	 */
	private int type;
	
	/**
	 * 状态: 0未满足条件 1待领取 2已领取
	 */
	private int status;
	
	/**
	 * tag跳转
	 */
	private String goPageTag;
	/**
	 * 跳转参数
	 */
	private String goPageParam;
	
	/**
	 * 0不可点击, 1可点击
	 */
	private int hitType;
	
	/**
	 * 点击跳转的url
	 */
	private String goUrl;
	
	/**
	 * 跳转url的title
	 */
	private String goUrlTitle;
	
	/**
	 * 未满足条件下的执行类型: 0点击不可执行;1点击后跳转内页,不消除此项;2点击后跳转到web页,不消除此项
	 */
	private int execType;
	
	/**
	 * 领取奖励金币数
	 */
	private int gold;
	
	/**
	 * 道具id
	 */
	private long propId;
	
	/**
	 * 人民币
	 */
	private int rmb;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGoPageTag() {
		return goPageTag;
	}

	public void setGoPageTag(String goPageTag) {
		this.goPageTag = goPageTag;
	}

	public String getGoPageParam() {
		return goPageParam;
	}

	public void setGoPageParam(String goPageParam) {
		this.goPageParam = goPageParam;
	}

	public int getHitType() {
		return hitType;
	}

	public void setHitType(int hitType) {
		this.hitType = hitType;
	}

	public String getGoUrl() {
		return goUrl;
	}

	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}

	public String getGoUrlTitle() {
		return goUrlTitle;
	}

	public void setGoUrlTitle(String goUrlTitle) {
		this.goUrlTitle = goUrlTitle;
	}

	public int getExecType() {
		return execType;
	}

	public void setExecType(int execType) {
		this.execType = execType;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public long getPropId() {
		return propId;
	}

	public void setPropId(long propId) {
		this.propId = propId;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}
	
}
