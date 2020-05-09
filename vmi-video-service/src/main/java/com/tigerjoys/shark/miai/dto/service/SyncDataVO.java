package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 同步数据类型VO对象
 * @author shiming
 *
 */
public class SyncDataVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7573604756376773918L;

	/**
     * 版本号
     */
    private Integer version;

    /**
     * 静态页地址列表
     */
    private Map<String,StaticWebUrlDto> mapStaticWeb;
    
    /**
     * 主播Tab列表
     */
    private List<HomeTabTypeDto> homeTabList;
    
    /**
     * 短视频Tab表表
     */
    private List<HomeTabTypeDto> homeVideoTabList;
    
    
    private List<Map<String, Object>> gifts;
    
    private Map<Integer,List<Map<String, Object>>> giftAllList;
    
    /**
     * 视频聊天截图时间间隔 毫秒
     */
    private long chatVideoSnapshotTime;
    
    
    /**
     * 视频聊天截图上传地址
     */
    private String chatVideoSnapshotUploadUrl;
    
    
    /**
     * 每间隔多少毫秒 上传一次音频快照
     */
    private long chatAudioSnapshotTime;

    /**
     * 音频快照上传地址
     */
    private String chatAudioSnapshotUploadUrl;

    /**
     * 音频快照是否开启
     * 0关闭 1开启
     */
    private int chatAudioSnapshotState;
    
    
    /**
     * 文字聊天页提示文字
     */
    private String chatHitInfo;
    
    
    /**
     * 聊天语音上传地址
     */
    private String chatAudioUploadUrl;
    
    
    /**
     * 聊天图片上传地址
     */
    private String chatPicUploadUrl;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Map<String, StaticWebUrlDto> getMapStaticWeb() {
		return mapStaticWeb;
	}

	public void setMapStaticWeb(Map<String, StaticWebUrlDto> mapStaticWeb) {
		this.mapStaticWeb = mapStaticWeb;
	}

	public List<HomeTabTypeDto> getHomeTabList() {
		return homeTabList;
	}

	public void setHomeTabList(List<HomeTabTypeDto> homeTabList) {
		this.homeTabList = homeTabList;
	}

	public List<HomeTabTypeDto> getHomeVideoTabList() {
		return homeVideoTabList;
	}

	public void setHomeVideoTabList(List<HomeTabTypeDto> homeVideoTabList) {
		this.homeVideoTabList = homeVideoTabList;
	}

	public List<Map<String, Object>> getGifts() {
		return gifts;
	}

	public void setGifts(List<Map<String, Object>> gifts) {
		this.gifts = gifts;
	}

	public String getChatVideoSnapshotUploadUrl() {
		return chatVideoSnapshotUploadUrl;
	}

	public void setChatVideoSnapshotUploadUrl(String chatVideoSnapshotUploadUrl) {
		this.chatVideoSnapshotUploadUrl = chatVideoSnapshotUploadUrl;
	}

	public long getChatVideoSnapshotTime() {
		return chatVideoSnapshotTime;
	}

	public void setChatVideoSnapshotTime(long chatVideoSnapshotTime) {
		this.chatVideoSnapshotTime = chatVideoSnapshotTime;
	}

	public String getChatHitInfo() {
		return chatHitInfo;
	}

	public void setChatHitInfo(String chatHitInfo) {
		this.chatHitInfo = chatHitInfo;
	}

	public long getChatAudioSnapshotTime() {
		return chatAudioSnapshotTime;
	}

	public void setChatAudioSnapshotTime(long chatAudioSnapshotTime) {
		this.chatAudioSnapshotTime = chatAudioSnapshotTime;
	}

	public String getChatAudioSnapshotUploadUrl() {
		return chatAudioSnapshotUploadUrl;
	}

	public void setChatAudioSnapshotUploadUrl(String chatAudioSnapshotUploadUrl) {
		this.chatAudioSnapshotUploadUrl = chatAudioSnapshotUploadUrl;
	}

	public int getChatAudioSnapshotState() {
		return chatAudioSnapshotState;
	}

	public void setChatAudioSnapshotState(int chatAudioSnapshotState) {
		this.chatAudioSnapshotState = chatAudioSnapshotState;
	}

	public Map<Integer, List<Map<String, Object>>> getGiftAllList() {
		return giftAllList;
	}

	public void setGiftAllList(Map<Integer, List<Map<String, Object>>> giftAllList) {
		this.giftAllList = giftAllList;
	}

	public String getChatAudioUploadUrl() {
		return chatAudioUploadUrl;
	}

	public void setChatAudioUploadUrl(String chatAudioUploadUrl) {
		this.chatAudioUploadUrl = chatAudioUploadUrl;
	}

	public String getChatPicUploadUrl() {
		return chatPicUploadUrl;
	}

	public void setChatPicUploadUrl(String chatPicUploadUrl) {
		this.chatPicUploadUrl = chatPicUploadUrl;
	}
	
	
	
}
