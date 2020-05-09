package com.tigerjoys.shark.miai.agent.dto;

public class VChatTCPInfoNotifyDto {
	/**
	 * 文本
	 */
	private String text;
	/**
	 * 文本颜色
	 */
	private String textColor;
	/**
	 * 文本背影
	 */
	private String textShadowColor;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getTextShadowColor() {
		return textShadowColor;
	}

	public void setTextShadowColor(String textShadowColor) {
		this.textShadowColor = textShadowColor;
	}

}
