package com.tigerjoys.shark.miai.dto.service;

/**
 * 预约业务的dto
 * @author shiming
 *
 */
public class Subscribe extends UserBaseInfo {

	/**
     * 0都不显示 1显示音频 2显示视频
     */
    private int btnAction;
    
    /**
     * 预约状态文字颜色
     */
    private String textColor;

	public int getBtnAction() {
		return btnAction;
	}

	public void setBtnAction(int btnAction) {
		this.btnAction = btnAction;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
    
}
