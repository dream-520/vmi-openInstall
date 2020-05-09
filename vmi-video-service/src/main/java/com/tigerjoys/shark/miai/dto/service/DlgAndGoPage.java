package com.tigerjoys.shark.miai.dto.service;

import java.util.HashMap;

import com.tigerjoys.nbs.common.utils.JsonHelper;

public class DlgAndGoPage {

	/**
	 * 提示信息
	 */
	private String hintInfo;
	
	/**
	 * 按钮名称  （单按钮不传，2个按钮，转值， btnName 有值验证内页跳转 ）
	 */
	private String btnName;
	
	/**
     * 按钮名称  （单按钮不传，2个按钮，转值， btnName2 第二个按钮的文字 ）
     */
    private String btnName2;
    
    /**
     * 第二个按钮要执行动作  大于0 标示有动作
     */
    private int action2;
    
	
	/**
	 * android内页标识
	 */
	private  String  androidPageTag;
	
	/**
	 * android内页标识参数
	 */
	private String androidPageParam;
	
	
	/**
	 * IOS内页标识
	 */
	private  String  iosPageTag;
	
	/**
	 * ISO内页标识参数
	 */
	private String iosPageParam;
	
	public static String getTagParam(Object ... param){
		HashMap<String, Object> hsmp = new HashMap<>(); 
		int i= 0;
		for( Object re:param){
			i++;
			hsmp.put("strValue"+i, re);
		}
		return JsonHelper.toJson(hsmp);
	}

	public String getHintInfo() {
		return hintInfo;
	}

	public void setHintInfo(String hintInfo) {
		this.hintInfo = hintInfo;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
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
		return iosPageParam;
	}

	public void setIosPageParam(String iosPageParam) {
		this.iosPageParam = iosPageParam;
	}

	public String getBtnName2() {
		return btnName2;
	}

	public void setBtnName2(String btnName2) {
		this.btnName2 = btnName2;
	}

	public int getAction2() {
		return action2;
	}

	public void setAction2(int action2) {
		this.action2 = action2;
	}
	
}
