package com.tigerjoys.shark.miai.dto.service;

/**
 * 2.2.0以后版本完善个人资料传递过来的参数Dto
 * @author lipeng
 *
 */
public class RegistDto {
	
	/**
	 * open id
	 */
	private String openid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 经度
	 */
	private Double lng;
	
	/**
	 * 纬度
	 */
	private Double lat;
	
	/**
	 * 注册来源,1微信注册,2QQ注册,3手机注册
	 */
	private Integer fr;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getFr() {
		return fr;
	}

	public void setFr(Integer fr) {
		this.fr = fr;
	}

}
