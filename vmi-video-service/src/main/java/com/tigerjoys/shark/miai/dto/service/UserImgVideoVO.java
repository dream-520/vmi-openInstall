package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户形象视频信息
 * @author lipeng
 *
 */
public class UserImgVideoVO implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2023924040884490199L;
	/**
     * 形象视频
     */
    private String videoPath;
    /**
     * 形象视频图片
     */
    private String videoPicture;
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public String getVideoPicture() {
		return videoPicture;
	}
	public void setVideoPicture(String videoPicture) {
		this.videoPicture = videoPicture;
	}

}
