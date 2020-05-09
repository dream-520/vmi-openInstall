package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户信用记录DTO
 * @author 刘满
 *
 */
public class UserInviteCpsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304839406652140446L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 昵称
	 */
	private String nikeName;
	
	/**
	 * 头像
	 */
	private String icon;
	
	
	/**
	 * 收益
	 */
	private String income;
	
	/**
	 * 创建时间
	 */
	private String createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
}