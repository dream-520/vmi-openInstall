package com.tigerjoys.shark.miai.dto.service;

/**
 * 登录接口添加一个对象专门处理动态隐藏
 * @author lipeng
 *
 */
public class DynamicUserDataDto {
	 /**
     * 是否是vip 1 是 0 不是
     */
    private int isVip;
    
    /**
     * 是否展示约会  0 不展示 1  展示
     */
    private int showWeb;

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

	public int getShowWeb() {
		return showWeb;
	}

	public void setShowWeb(int showWeb) {
		this.showWeb = showWeb;
	}
    
    
}
