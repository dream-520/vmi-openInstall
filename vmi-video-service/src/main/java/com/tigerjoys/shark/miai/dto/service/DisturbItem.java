package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户勿扰模式中的勿扰条目
 * @author shiming
 *
 */
public class DisturbItem {

    /**
     * 主播id
     */
    private long userId;
    /**
     * 主播昵称
     */
    private String nickName;

    /**
     * 勿扰设置是否开启 0 未开启 1 开启
     */
    private int status;
    
    /**
     * 主播图像
     */
    private String photo;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
    
}
