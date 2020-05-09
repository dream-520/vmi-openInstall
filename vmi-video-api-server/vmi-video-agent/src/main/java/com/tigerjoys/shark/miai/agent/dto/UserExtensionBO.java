package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户扩展信息DTO
 * @author chengang
 *
 */
public class UserExtensionBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1420962255631226221L;
	
	/**
	 * ID
	 */
	private long id;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 星座
	 */
	private String zodiac;
	
	/**
	 * 感情状态
	 */
	private int marriage;
	
	/**
	 * 职位
	 */
	private String job;
	
	/**
	 * 收入
	 */
	private int income;
	
	/**
	 * 身高(CM)
	 */
	private int stature;
	
	/**
	 * 体重(KG)
	 */
	private int weight;
	
	/**
	 * 对性的看法
	 */
	private int sexOpinion;
	
	/**
	 * 对另一半的看法
	 */
	private int spouseOpinion;
	
	/**
	 * 交友目地
	 */
	private int makeFriend;
	
	/**
	 * 特点
	 */
	private String traitPoint;
	
	/**
	 * 扩展JSON参数，必不为空
	 */
	private JSONObject extraJson;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public int getMarriage() {
		return marriage;
	}

	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getStature() {
		return stature;
	}

	public void setStature(int stature) {
		this.stature = stature;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSexOpinion() {
		return sexOpinion;
	}

	public void setSexOpinion(int sexOpinion) {
		this.sexOpinion = sexOpinion;
	}

	public int getSpouseOpinion() {
		return spouseOpinion;
	}

	public void setSpouseOpinion(int spouseOpinion) {
		this.spouseOpinion = spouseOpinion;
	}

	public int getMakeFriend() {
		return makeFriend;
	}

	public void setMakeFriend(int makeFriend) {
		this.makeFriend = makeFriend;
	}

	public String getTraitPoint() {
		return traitPoint;
	}

	public void setTraitPoint(String traitPoint) {
		this.traitPoint = traitPoint;
	}

	public JSONObject getExtraJson() {
		return extraJson;
	}

	public void setExtraJson(JSONObject extraJson) {
		this.extraJson = extraJson;
	}

}
