package com.tigerjoys.shark.miai.agent.dto;

/**
 * 个人主页Top4守护实体
 * @author shiming
 *
 */
public class AnchorDefendTopDto {

	private String photo;
	 
	private String guardLevel;
	
	private int level;
	
	private int close;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getGuardLevel() {
		return guardLevel;
	}

	public void setGuardLevel(String guardLevel) {
		this.guardLevel = guardLevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}
	 
}
