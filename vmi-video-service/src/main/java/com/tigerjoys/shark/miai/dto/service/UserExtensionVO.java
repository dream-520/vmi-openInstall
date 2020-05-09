package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 用户扩展信息
 * @author chengang
 *
 */
public class UserExtensionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1453535035648118897L;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 星座
	 */
	private String zodiac;
	
	/**
	 * 感情状态
	 */
	private Integer marriage;
	
	/**
	 * 职位
	 */
	private String job;
	
	/**
	 * 收入
	 */
	private Integer income;
	
	/**
	 * 身高(CM)
	 */
	private Integer stature;
	
	/**
	 * 体重(KG)
	 */
	private Integer weight;
	
	/**
	 * 对性的看法
	 */
	private Integer sexOpinion;
	
	/**
	 * 对另一半的看法
	 */
	private Integer spouseOpinion;
	
	/**
	 * 交友目地
	 */
	private Integer makeFriend;
	
	/**
	 * 特点
	 */
	private String[] traitPoint;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getSexOpinion() {
		return sexOpinion;
	}

	public void setSexOpinion(Integer sexOpinion) {
		this.sexOpinion = sexOpinion;
	}

	public Integer getSpouseOpinion() {
		return spouseOpinion;
	}

	public void setSpouseOpinion(Integer spouseOpinion) {
		this.spouseOpinion = spouseOpinion;
	}

	public Integer getMakeFriend() {
		return makeFriend;
	}

	public void setMakeFriend(Integer makeFriend) {
		this.makeFriend = makeFriend;
	}

	public String[] getTraitPoint() {
		return traitPoint;
	}

	public void setTraitPoint(String[] traitPoint) {
		this.traitPoint = traitPoint;
	}

}
