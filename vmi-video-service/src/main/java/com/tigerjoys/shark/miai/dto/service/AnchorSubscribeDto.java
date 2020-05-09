package com.tigerjoys.shark.miai.dto.service;

public class AnchorSubscribeDto {
	
    /**
     * 用户昵称
     */
    private String nickName;
    
    /**
     * 用户图像
     */
    private String photo;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 是否预约 0 否 1 是
     */
    private int status;
    
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
    
}
