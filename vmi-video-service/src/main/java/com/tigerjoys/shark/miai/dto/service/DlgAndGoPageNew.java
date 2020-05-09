package com.tigerjoys.shark.miai.dto.service;

import java.util.HashMap;
import java.util.List;

import com.tigerjoys.nbs.common.utils.JsonHelper;

public class DlgAndGoPageNew {


    /**
     * 提示信息
     */
    private String hintInfo;

    /**
     * 
     */
    private List<BtnData> btnDataList;

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
    
    
    /**
     * ios专用
	 * 按钮名称  （单按钮不传，2个按钮，转值， btnName 有值验证内页跳转 ）
	 */
	private String iosBtnName;
    /**
     * ios内页标识
     */
    private String iosPageTag;

    /**
     * ios内页标识参数
     */
    private String IosPageParam;

	public String getHintInfo() {
		return hintInfo;
	}

	public void setHintInfo(String hintInfo) {
		this.hintInfo = hintInfo;
	}

	public List<BtnData> getBtnDataList() {
		return btnDataList;
	}

	public void setBtnDataList(List<BtnData> btnDataList) {
		this.btnDataList = btnDataList;
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

	public String getIosPageTag() {
		return iosPageTag;
	}

	public void setIosPageTag(String iosPageTag) {
		this.iosPageTag = iosPageTag;
	}

	public String getIosPageParam() {
		return IosPageParam;
	}

	public void setIosPageParam(String iosPageParam) {
		IosPageParam = iosPageParam;
	}

	public String getIosBtnName() {
		return iosBtnName;
	}

	public void setIosBtnName(String iosBtnName) {
		this.iosBtnName = iosBtnName;
	}

	
	
	
}
