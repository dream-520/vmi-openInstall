package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 创建大v信息实体
 * @author shiming
 *
 */
public class AnchorCreateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7062719893300859784L;

	/**
	 * 标题
	 */
	@NotBlank(message = "昵称不能为空")
	private String nickName;
	
	/**
	 * 手机号码
	 */
	@NotBlank(message = "手机号码不能为空")
	private String mobile;
	
	/**
	 * 身高
	 */
	@NotBlank(message = "身高不能为空")
	private String stature;
	
	/**
	 * 体重
	 */
	@NotBlank(message = "体重不能为空")
	private String weight;
	
	/**
	 * 星座
	 */
	@NotBlank(message = "星座不能为空")
	private String zodiac;
	
	/**
	 * 城市
	 */
	@NotBlank(message = "请选择城市")
	private String cityId;
	
	/**
	 * 个性签名
	 */
	@NotBlank(message = "个性签名不能为空")
	private String signature;
	
	/**
	 * 个人介绍
	 */
	@NotBlank(message = "个人介绍不能为空")
	private String intro;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
