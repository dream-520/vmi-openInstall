package com.tigerjoys.shark.miai.dto.service;

public class BtnData {
	 /**
     * 按钮名称
     */
    private String btnName;

    /**
     * 要执行动作  大于0 标示有动作
     */
    private int action;

    /**
     * android内页标识
     */
    private String androidPageTag;

    /**
     * android内页标识参数
     */
    private String androidPageParam;
    
    public BtnData() {
    	
    }
    
    public BtnData(String btnName) {
    	this.btnName = btnName;
    }

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getAndroidPageTag() {
		return androidPageTag;
	}

	public void setAndroidPageTag(String androidPageTag) {
		this.androidPageTag = androidPageTag;
	}

	public String getAndroidPageParam() {
		return androidPageParam;
	}

	public void setAndroidPageParam(String androidPageParam) {
		this.androidPageParam = androidPageParam;
	}


}
