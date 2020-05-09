package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户领取奖品记录dto
 * @author lipeng
 *
 */
public class CommodityShipmentsDto{

	/**
	 * Id
	 */
	private long id;
	/**
	 * 商品图片
	 */
	private String photo;
	
	/**
	 * 商品标题
	 */
	private String name;
	
	/**
	 * 创建时间
	 */
	private String creat_time;
	
	/**
	 * 发货状态
	 */
	private Integer status;

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

	public String getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
