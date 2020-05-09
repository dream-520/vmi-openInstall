package com.tigerjoys.shark.miai.agent.utils;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.utils.JsonHelper;

public class SmsParam {

	private Map<String, String> hsmp;
	
	public SmsParam() {
		this.hsmp = new HashMap<>();
	}
	
	public SmsParam(Map<String , String> hsmap) {
		if(hsmap == null) {
			hsmap = new HashMap<>();
		}
		this.hsmp = hsmap;
	}

	public static SmsParam getSmsParam(String key, String value) {
		SmsParam helper = new SmsParam();
		helper.add(key, value);
		return helper;
	}

	public SmsParam add(String key, String value) {
		hsmp.put(key, value);
		return this;
	}

	public String toJson() {
		return JsonHelper.toJson(hsmp);
	}

}
