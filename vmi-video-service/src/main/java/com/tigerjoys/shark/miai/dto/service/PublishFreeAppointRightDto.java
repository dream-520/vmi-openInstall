package com.tigerjoys.shark.miai.dto.service;

/**
 * 用户发布普通用户权限dto
 * @author liuman
 *
 */
public class PublishFreeAppointRightDto {
	 /**
     * 1允许  2不允许
     */
    private int tag;

    /**
     * 提示文字
     */
    private String hintText;

    /**
     * app页名
     */
    private String appTag;

    /**
     * 参数
     */
    private String parameter;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getHintText() {
		return hintText;
	}

	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

	public String getAppTag() {
		return appTag;
	}

	public void setAppTag(String appTag) {
		this.appTag = appTag;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
    
}
