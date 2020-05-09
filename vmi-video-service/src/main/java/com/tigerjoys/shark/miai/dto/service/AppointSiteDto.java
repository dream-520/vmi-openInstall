package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.enums.AppointSiteTypeEnum;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteEntity;

/**
 * 场地dto
 * @author yangjunming
 *
 */
public class AppointSiteDto  {
	
	/**
	 * id
	 */
	private long id;
	
	/**
	 * 类别
	 */
	private String type;

	/**
	 * 场地名称
	 */
	private String title;
	
	/**
	 * 场地图片地址
	 */
	private String picture;
	
	
	/**
	 * 星级
	 */
	private byte[] rating;
	
	/**
	 * 灰星级
	 */
	private byte[] rating2;

	/**
	 * 场地使用事项
	 */
	private String matters;
	
	/**
	 * 场地地址
	 */
	private String address;
	
	/**
	 * 场地信息
	 */
	private String info;
	
	/**
	 * 场地营业时间
	 */
	private String businessTime;
	
	/**
	 * 场地设施
	 */
	private String facilities;
	
	
	/**
	 * 场地费用
	 */
	private Integer cost;

	/**
	 * 客服咨询
	 */
	private String telephone;
	
	/**
	 * 创建时间
	 */
	private String create_time;

	
	public  static AppointSiteDto pareDto(AppointSiteEntity entity){
		AppointSiteDto dto=new AppointSiteDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setType(AppointSiteTypeEnum.getByCode(entity.getType()).getDesc());
		dto.setPicture(Const.getCdn(entity.getPicture()));
		dto.setRating(new byte[entity.getRating()>=0?entity.getRating():0]);
		dto.setRating2(new byte[5-entity.getRating()>=0?5-entity.getRating():0]);
		dto.setMatters(entity.getMatters());
		dto.setAddress(entity.getAddress());
		dto.setInfo(entity.getInfo());
		dto.setBusinessTime(entity.getBusiness_time());
		dto.setCost(entity.getCost());
		dto.setTelephone(entity.getTelephone());
		dto.setCreate_time(Tools.getDate(entity.getCreate_time()));
		return dto;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getMatters() {
		return matters;
	}


	public void setMatters(String matters) {
		this.matters = matters;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getBusinessTime() {
		return businessTime;
	}


	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}


	public String getFacilities() {
		return facilities;
	}


	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}


	public Integer getCost() {
		return cost;
	}


	public void setCost(Integer cost) {
		this.cost = cost;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public byte[] getRating() {
		return rating;
	}


	public void setRating(byte[] rating) {
		this.rating = rating;
	}


	public byte[] getRating2() {
		return rating2;
	}


	public void setRating2(byte[] rating2) {
		this.rating2 = rating2;
	}

	
}
