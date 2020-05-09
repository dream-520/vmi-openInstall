package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 排行榜数据实体
 * @author shiming
 *
 */
public class AppRankingDto implements Serializable {

	private static final long serialVersionUID = -7885373656596157024L;

	/**
	 * 用户ID
	 */
    private long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头象
     */
    private String photo;
    
    /**
     * 主播星级
     */
    private int anchorStar;
    
    /**
     * 普通用户等级
     */
    private String normalLevel;
	/**
     * 显示内容财富值/魅力值
     */
    private String content;
    
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getAnchorStar() {
		return anchorStar;
	}
	public void setAnchorStar(int anchorStar) {
		this.anchorStar = anchorStar;
	}
	public String getNormalLevel() {
		return normalLevel;
	}
	public void setNormalLevel(String normalLevel) {
		this.normalLevel = normalLevel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
}
