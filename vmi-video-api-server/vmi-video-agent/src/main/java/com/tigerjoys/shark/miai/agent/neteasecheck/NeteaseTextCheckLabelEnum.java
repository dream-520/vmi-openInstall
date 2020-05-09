package com.tigerjoys.shark.miai.agent.neteasecheck;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.utils.Tools;

import ch.qos.logback.core.joran.conditional.IfAction;

/**
 * 用于区分动态请求类型的枚举
 * @author yangjunming
 *
 */
public enum NeteaseTextCheckLabelEnum {
	//
	porn(100,"色情"),
	advertising(200,"广告"),
	violence(300,"暴恐"),
	prohibition(400,"违禁"),
	politics(500,"涉政"),
	abuse(600,"谩骂"),
	irrigation(700,"灌水"),
	other(900,"其他"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , NeteaseTextCheckLabelEnum> STATUS_DESC = new HashMap<Integer , NeteaseTextCheckLabelEnum>();
	
	static {
		for(NeteaseTextCheckLabelEnum refer : NeteaseTextCheckLabelEnum.values()) {
			STATUS_DESC.put(refer.getCode(), refer);
		}
	}
	
	private NeteaseTextCheckLabelEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static NeteaseTextCheckLabelEnum getByCode(int code) {
		NeteaseTextCheckLabelEnum errCode = STATUS_DESC.get(code);
		if(errCode == null) {
			return null;
		}
		return errCode;
	}
	
	public static String getDescByCode(int code) {
		NeteaseTextCheckLabelEnum errCode = STATUS_DESC.get(code);
		if(errCode == null) {
			NeteasePictureCheckLabelEnum pic = NeteasePictureCheckLabelEnum.getByCode(code);
			return Tools.isNotNull(pic)?pic.getDesc():"";
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
