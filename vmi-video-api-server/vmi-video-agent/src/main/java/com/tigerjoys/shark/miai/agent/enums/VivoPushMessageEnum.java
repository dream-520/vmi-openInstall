package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送枚举
 * @author lipeng
 *
 */
public enum VivoPushMessageEnum {
	vivo_com_ydwx_yoyo("com.ydwx.yoyo",1,"V密"), 
	vivo_com_ydwx_yoyo3("com.ydwx.yoyo3",2,"密约"), 
	vivo_com_ydwx_yoyo2("com.ydwx.yoyo2",3,"陌约"), 
 	;
	/**
	 * 包名
	 */
	private String code;
	/**
	 * 服务类后缀与 配值数据后缀
	 */
	private int impl;
	
	/**
	 * 包名
	 */
	private String desc;
	
	public static final String com_ydwx_yoyo = "vivo_com_ydwx_yoyo";
	public static final String com_ydwx_yoyo3 = "vivo_com_ydwx_yoyo3";
	public static final String com_ydwx_yoyo2 = "vivo_com_ydwx_yoyo2";
	
	private static final Map<String , VivoPushMessageEnum> err_desc = new HashMap<String , VivoPushMessageEnum>();
	
	static {
		for(VivoPushMessageEnum refer : VivoPushMessageEnum.values()) {
			err_desc.put(refer.getCode(), refer);
		}
	}
	
	private VivoPushMessageEnum(String code,int impl, String desc) {
		this.code = code;
		this.impl = impl;
		this.desc = desc;
	}
	
	public static VivoPushMessageEnum getByCode(String code) {
		return err_desc.get(code);
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getImpl() {
		return impl;
	}

	public void setImpl(int impl) {
		this.impl = impl;
	}
	
	

}
