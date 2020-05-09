package com.tigerjoys.shark.miai.dto.service;

import org.shark.miai.common.enums.ShortVideoStatusEnum;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 短视频首页DTO
 * 
 * @author yangjunming
 *
 */
public class VideoHomeDto {

	/**
	 * 短视频ID
	 */
	private long videoId;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 视频封面
	 */
	private String videoPhoto;

	/**
	 * 视频地址
	 */
	private String videoPath;

	/**
	 * 视频点赞数
	 */
	private String videoPraise;
	
	
	  /**
     * 审核文字
     */
    private String verifyText;

	public static VideoHomeDto preDto(ShortVideoEntity entity){
		VideoHomeDto dto = new VideoHomeDto();
		dto.setVideoId(entity.getId());
		dto.setUserId(entity.getUserid());
		dto.setVideoPhoto(ServiceHelper.getCdnPhoto(entity.getVideo_photo()));
		dto.setVideoPath(ServiceHelper.getCdnVideo(entity.getVideo_path()));
		dto.setVideoPraise(""+entity.getVideo_praise());
		dto.setVerifyText(entity.getStatus() == ShortVideoStatusEnum.audit.getCode() || entity.getStatus() == ShortVideoStatusEnum.transcoding.getCode()? "审核中":"" );
		return dto;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getVideoPhoto() {
		return videoPhoto;
	}

	public void setVideoPhoto(String videoPhoto) {
		this.videoPhoto = videoPhoto;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getVideoPraise() {
		return videoPraise;
	}

	public void setVideoPraise(String videoPraise) {
		this.videoPraise = videoPraise;
	}

	public String getVerifyText() {
		return verifyText;
	}

	public void setVerifyText(String verifyText) {
		this.verifyText = verifyText;
	}

	
}
