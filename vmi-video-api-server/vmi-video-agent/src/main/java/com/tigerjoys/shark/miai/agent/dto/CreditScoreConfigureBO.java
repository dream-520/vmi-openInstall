package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 信用分购买配置DTO
 * @author 刘满
 *
 */
public class CreditScoreConfigureBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4681559021342189831L;

	/**
	 * 信用分配置ID
	 */
	private Long id;
	
	/**
	 * 主题
	 */
	private String theme;
	
	/**
	 * 副主题
	 */
	private String sub_theme;
	
	/**
	 * 信用账户类型:1-普通用户,2-服务者用户
	 */
	private Integer type;
	
	/**
	 * 信用分
	 */
	private Integer score;
	
	/**
	 * 购买信用分需要的钻石数
	 */
	private Integer diamonds;
	
//	/**
//	 * 信用账户剩余分数
//	 */
//	private Integer balanceScore;
//	
//	/**
//	 * 钻石账户剩余钻石数
//	 */
//	private Integer balanceDiamonds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSub_theme() {
		return sub_theme;
	}

	public void setSub_theme(String sub_theme) {
		this.sub_theme = sub_theme;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(Integer diamonds) {
		this.diamonds = diamonds;
	}

}