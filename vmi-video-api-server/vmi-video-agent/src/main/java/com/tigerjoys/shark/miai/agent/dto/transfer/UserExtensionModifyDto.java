package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户修改传递的DTO
 * @author chengang
 *
 */
public class UserExtensionModifyDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2324333487252839878L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 星座
	 */
	private String zodiac;
	
	/**
	 * 感情状态id
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
	private String traitPoint;
	
	/**
	 * 额外的参数，传入value值为null，则代表删除。
	 */
	private JSONObject addExtraJson;

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

	public String getTraitPoint() {
		return traitPoint;
	}

	public void setTraitPoint(String traitPoint) {
		this.traitPoint = traitPoint;
	}
	
	/**
	 * 判断扩展参数是否为空
	 * @return isEmptyParamJson
	 */
	public boolean isEmptyParamJson() {
		return addExtraJson == null || addExtraJson.isEmpty();
	}
	
	/**
	 * 获得当前的修改的扩展JSON对象
	 * @return JSONObject
	 */
	public JSONObject getParamJson() {
		if (addExtraJson == null) {
			addExtraJson = new JSONObject();
		}
		return addExtraJson;
	}
	
	/**
	 * 向扩展JSON对象添加参数
	 * @param key - 键
	 * @param value - 值
	 */
	public void addParamJson(String key , Object value) {
		getParamJson().put(key, value);
	}

}
