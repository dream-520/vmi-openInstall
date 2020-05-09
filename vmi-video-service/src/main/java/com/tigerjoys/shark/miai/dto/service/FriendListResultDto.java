package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 好友列表返回DTO
 * 
 * @author shiming
 *
 */
public class FriendListResultDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757834390785677285L;

	/**
	 * userfriend表ID
	 */
	private long id;

	/**
	 * 好友ID
	 */
	private Long userId;
	
	/**
	 * 好友名
	 */
	private String nickName;

	/**
	 * 头像
	 */
	private String faceImg;

	/**
	 * 用户宣言
	 */
	private String declaration;
	
	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 性别 1:男 2：女
	 */
	private int sex;

	/**
	 * 好友之间的状态
	 */
	private int status;
	
	/**
	 * 是否是vip
	 */
	private int vip;
	
	/**
	 * 余额
	 */
	private String balance;
	
	/**
	 * 状态
	 */
	private String stateStr;
	
	/**
	 * 是不是主播 0不是 1是
	 */
	private int isWaiter;
	
    /**
     * 是否是音频主播 0不是 1是
     */
    private int anchorTypeAudio;

    /**
     * 是否是视频主播 0不是 1是
     */
    private int anchorTypeVideo;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public int getIsWaiter() {
		return isWaiter;
	}

	public void setIsWaiter(int isWaiter) {
		this.isWaiter = isWaiter;
	}
	
	public int getAnchorTypeAudio() {
		return anchorTypeAudio;
	}

	public void setAnchorTypeAudio(int anchorTypeAudio) {
		this.anchorTypeAudio = anchorTypeAudio;
	}

	public int getAnchorTypeVideo() {
		return anchorTypeVideo;
	}

	public void setAnchorTypeVideo(int anchorTypeVideo) {
		this.anchorTypeVideo = anchorTypeVideo;
	}
}
