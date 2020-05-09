package com.tigerjoys.shark.miai.dto.service;

public class AnchorAudioDto {

    /**
     * 录音id
     */
    private long audioId;
    /**
     * 录音播放地址
     */
    private String url;
    /**
     *  录音时长
     */
    private String audioTime;
    
    /**
     * 0未选中 1选中
     */
    private int status;
    
    /**
     * 审核文字
     */
    private String verifyText;

	public long getAudioId() {
		return audioId;
	}

	public void setAudioId(long audioId) {
		this.audioId = audioId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAudioTime() {
		return audioTime;
	}

	public void setAudioTime(String audioTime) {
		this.audioTime = audioTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getVerifyText() {
		return verifyText;
	}

	public void setVerifyText(String verifyText) {
		this.verifyText = verifyText;
	}

}
