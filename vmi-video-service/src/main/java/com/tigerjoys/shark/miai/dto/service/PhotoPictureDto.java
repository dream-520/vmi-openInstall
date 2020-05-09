package com.tigerjoys.shark.miai.dto.service;

/**
 * 相册使用的Dto
 * @author shiming
 */
public class PhotoPictureDto {
	
	private Long photoId;

	private String smallUrl;
	
	private String bigUrl;
	
	/**
     * 审核文字
     */
    private String verifyText;

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public String getBigUrl() {
		return bigUrl;
	}

	public void setBigUrl(String bigUrl) {
		this.bigUrl = bigUrl;
	}

	public String getVerifyText() {
		return verifyText;
	}

	public void setVerifyText(String verifyText) {
		this.verifyText = verifyText;
	}
	
}
