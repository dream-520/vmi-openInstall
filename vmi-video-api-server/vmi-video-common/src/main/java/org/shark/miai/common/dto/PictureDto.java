package org.shark.miai.common.dto;

import java.io.Serializable;

/**
 * 图片DTO
 * @author chengang
 *
 */
public class PictureDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920064738831872466L;
	
	/**
	 * 原始图片地址
	 */
	private String originPhoto;
	
	/**
	 * 图片地址
	 */
	private String photo;
	
	/**
	 * 小图地址
	 */
	private String smallPhoto;
	
	public PictureDto(String photo , String smallPhoto) {
		this.photo = photo;
		this.smallPhoto = smallPhoto;
	}
	
	public PictureDto(String photo , String smallPhoto , String originPhoto) {
		this.photo = photo;
		this.smallPhoto = smallPhoto;
		this.originPhoto = originPhoto;
	}

	public String getOriginPhoto() {
		return originPhoto;
	}

	public void setOriginPhoto(String originPhoto) {
		this.originPhoto = originPhoto;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSmallPhoto() {
		return smallPhoto;
	}

	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}

}
