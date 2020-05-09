package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户领取奖品dto
 * @author lipeng
 *
 */
public class CommodityDto{

	
	/**
	 * Id
	 */
	private long id;
	/**
	 * 商品图片
	 */
	private String photo;
	
	/**
	 * 商品名称
	 */
	private String name;

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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
