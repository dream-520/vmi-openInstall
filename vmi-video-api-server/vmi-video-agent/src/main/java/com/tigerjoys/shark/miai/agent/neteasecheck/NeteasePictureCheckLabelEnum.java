package com.tigerjoys.shark.miai.agent.neteasecheck;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态请求类型的枚举
 * @author yangjunming
 *
 */
public enum NeteasePictureCheckLabelEnum {
	//
	porn(100,"色情"),
	low(110,"性感低俗"),
	advertising(200,"广告"),
	QRCode(210,"二维码"),
	violence(300,"暴恐"),
	prohibition(400,"违禁"),
	politics(500,"涉政"),
	other(900,"其他"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , NeteasePictureCheckLabelEnum> STATUS_DESC = new HashMap<Integer , NeteasePictureCheckLabelEnum>();
	
	static {
		for(NeteasePictureCheckLabelEnum refer : NeteasePictureCheckLabelEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private NeteasePictureCheckLabelEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static NeteasePictureCheckLabelEnum getByCode(int code) {
		NeteasePictureCheckLabelEnum errCode = STATUS_DESC.get(code);
		if(errCode == null) {
			return null;
		}
		return errCode;
	}
	
	public static String getDescByCode(int code) {
		NeteasePictureCheckLabelEnum errCode = STATUS_DESC.get(code);
		if(errCode == null) {
			return "";
		}
		return errCode.getDesc();
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
