package com.tigerjoys.shark.miai.dto.service;

public class AnchorDefendItemDataDto extends UserBaseInfo {

	/**
	 * 守护类型图标
	 */
	private String guardLevel;

    /**
     * 亲密值
     */
    private String closeValue;
    
    /**
     * 亲密值
     */
    private int close;
    
    /**
     * 守护等级
     */
    private int level;

	public String getGuardLevel() {
		return guardLevel;
	}

	public void setGuardLevel(String guardLevel) {
		this.guardLevel = guardLevel;
	}

	public String getCloseValue() {
		return closeValue;
	}

	public void setCloseValue(String closeValue) {
		this.closeValue = closeValue;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
    
}
