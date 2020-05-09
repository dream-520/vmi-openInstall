package com.tigerjoys.shark.miai.dto.service;

/**
 * 全局广播
 * @author yangjunming
 *
 */
public class GlobalBroadcastDto {
	/**
     * 广播id
     */
    private long id;
    /**
     * 用户图像
     */
    private String photo;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 广播内容
     */
    private String content;
    
    /**
     * 
     */
    private String img;
    
    /**
     * 广播内容
     */
    private Integer type;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    
}
