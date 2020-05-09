package com.tigerjoys.shark.miai.dto.service;

import org.shark.miai.common.enums.ShortVideoStatusEnum;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 短视频详情
 * @author yangjunming
 *
 */
public class AnchorVideoWatchDto {
	
	/**
	 * 短视频ID
	 */
	private Long videoId;
	
	/**
	 * 用户信息
	 */
	private UserBaseInfo userInfo;
	
	/**
	 * 视频点赞数
	 */
	private String videoPraise;
	
	/**
	 * 视频点赞数
	 */
	private Boolean praiseFlag;
	
	
	/**
	 * 观看数
	 */
	private String watchNum;
	

	/**
	 * 视频封面
	 */
	private String anchorPhoto;
	
	/**
	 * 视频封面
	 */
	private String videoPhoto;
	
	/**
	 * 视频地址
	 */
	private String videoPath;
	
	/**
	 * 视频是否是私密视频，0否，1是
	 */
	private int obscure;
	
	/**
	 * 模糊图片地址
	 */
	private String obscurePath;
	
	/**
	 * 是否已购买,0否,1是
	 */
	private int buy;
	
	/**
	 * 解密私密视频的花费
	 */
	private int costDiamond;
	
	 /**
     * 音频地址
     */
    private String audioPath;

    /**
     * 音频展示文字
     */
    private String audioText;
    
    /**
     * 审核文字
     */
    private String verifyText;
    
    /**
     * 年龄展示文字
     */
    private String ageText;
    
    /**
     * 距离展示文字
     */
    private String distanceText;

    /**
     * 聊天状态 0：打招呼 1：私聊
     */
    private int chatType;

    /**
     * 生成视图对象
     * @param anchor - 主播对象
     * @param video - 视频对象
     * @param scale - ？？
     * @param showSourcePic - 如果为false，则视频如果是私密视频，则不展示其真实的地址和封面图片
     * @return AnchorVideoWatchDto
     */
	public static AnchorVideoWatchDto preDto(AnchorOnlineEntity anchor, ShortVideoEntity video, int scale, boolean showSourcePic){
		AnchorVideoWatchDto dto = new AnchorVideoWatchDto();
		if(Tools.isNotNull(anchor)){
			UserBaseInfo userInfo = new UserBaseInfo();
			userInfo.setUserId(anchor.getUserid());
			userInfo.setNickName(anchor.getNickname());
			userInfo.setAnchorStar(anchor.getStar());
			userInfo.setAnchorSignature(anchor.getSignature());
			userInfo.setAnchorStatus(anchor.getOnline());
			userInfo.setPhoto(ServiceHelper.getUserSmallPhoto(anchor.getPhoto()));
			//视频主播
			if(anchor.getVideo_type().intValue() > 0) {
				if(anchor.getPrice().intValue() > 0) {
					int price = anchor.getPrice() + (int)(1.0 * scale / 100 * anchor.getPrice());
					userInfo.setAnchorPrice(price + "钻/分钟");
				} else {
					userInfo.setAnchorPrice("");
				}
			}
			userInfo.setAnchorTypeVideo(anchor.getVideo_type());
			
			//音频主播
			if(anchor.getAudio_type().intValue() > 0) {
				//设置价格
				if(anchor.getAudio_price().intValue() > 0) {
					int price = anchor.getAudio_price() + (int)(1.0 * scale / 100 * anchor.getAudio_price());
					userInfo.setAnchorAudioPrice(price + "钻/分钟");
				} else {
					userInfo.setAnchorAudioPrice("");
				}
				//设置音频时间
				if(Tools.isNotNull(anchor.getAudio_time()) && anchor.getAudio_time().intValue() > 0)
					dto.setAudioText(anchor.getAudio_time()+"s");
				//设置音频路径
				if(Tools.isNotNull(anchor.getAudio_path()))
					dto.setAudioPath(ServiceHelper.getCdnVideo(anchor.getAudio_path()));
			}
			userInfo.setAnchorTypeAudio(anchor.getAudio_type());
			
			dto.setUserInfo(userInfo);
			dto.setAnchorPhoto(Const.getCdn(anchor.getPhoto()));
			
			//设置年龄和距离字段信息
			if(anchor.getAge() > 0)
				dto.setAgeText(anchor.getAge()+"岁");
			if(anchor.getDistance() > 0)
				dto.setDistanceText(anchor.getDistance()+"km");
		}
		if(Tools.isNotNull(video)){
			dto.setVideoId(video.getId());
			/*
			if(Const.IS_TEST){
				dto.setVideoPath(ServiceHelper.getCdnPhoto(video.getVideo_path()));
			}else{
				dto.setVideoPath(ServiceHelper.getCdnVideo(video.getVideo_path()));
			}
			*/
			dto.setObscure(Tools.intValue(video.getObscure()));
			//私密视频
			if(Tools.intValue(video.getObscure()) == 1) {
				//自己查看或者已购买
				if(showSourcePic) {
					dto.setBuy(1);
				} else {
					dto.setBuy(0);
				}
				dto.setVideoPhoto(ServiceHelper.getCdnPhoto(video.getVideo_photo()));
				dto.setObscurePath(ServiceHelper.getCdnPhoto(video.getObscure_path()));
			} else {
				dto.setBuy(0);
				dto.setVideoPhoto(ServiceHelper.getCdnPhoto(video.getVideo_photo()));
			}
			dto.setCostDiamond(Const.USER_VIDEO_PRIVACY_DIAMOND);
			dto.setVideoPraise(video.getVideo_praise()+"");
			dto.setVideoPath(ServiceHelper.getCdnVideo(video.getVideo_path()));
			dto.setWatchNum(video.getWatch_num()+"");
			if(video.getStatus()== ShortVideoStatusEnum.audit.getCode() ||video.getStatus()== ShortVideoStatusEnum.transcoding.getCode() ){
				dto.setVerifyText(ShortVideoStatusEnum.audit.getDesc());
			}
		}
		return dto;
		
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public UserBaseInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getVideoPraise() {
		return videoPraise;
	}

	public void setVideoPraise(String videoPraise) {
		this.videoPraise = videoPraise;
	}

	public Boolean getPraiseFlag() {
		return praiseFlag;
	}

	public void setPraiseFlag(Boolean praiseFlag) {
		this.praiseFlag = praiseFlag;
	}

	public String getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(String watchNum) {
		this.watchNum = watchNum;
	}

	public String getAnchorPhoto() {
		return anchorPhoto;
	}

	public void setAnchorPhoto(String anchorPhoto) {
		this.anchorPhoto = anchorPhoto;
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

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	public String getAudioText() {
		return audioText;
	}

	public void setAudioText(String audioText) {
		this.audioText = audioText;
	}

	public String getVerifyText() {
		return verifyText;
	}

	public void setVerifyText(String verifyText) {
		this.verifyText = verifyText;
	}

	public String getAgeText() {
		return ageText;
	}

	public void setAgeText(String ageText) {
		this.ageText = ageText;
	}

	public String getDistanceText() {
		return distanceText;
	}

	public void setDistanceText(String distanceText) {
		this.distanceText = distanceText;
	}

	public int getChatType() {
		return chatType;
	}

	public void setChatType(int chatType) {
		this.chatType = chatType;
	}

	public int getObscure() {
		return obscure;
	}

	public void setObscure(int obscure) {
		this.obscure = obscure;
	}

	public int getCostDiamond() {
		return costDiamond;
	}

	public void setCostDiamond(int costDiamond) {
		this.costDiamond = costDiamond;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public String getObscurePath() {
		return obscurePath;
	}

	public void setObscurePath(String obscurePath) {
		this.obscurePath = obscurePath;
	}

}
