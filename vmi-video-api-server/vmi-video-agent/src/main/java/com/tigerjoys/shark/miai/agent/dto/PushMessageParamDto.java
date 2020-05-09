package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 推送消息请求参数dto
 * 
 * @author liuman
 *
 */
public class PushMessageParamDto implements Serializable {
	
	private static final long serialVersionUID = 6734244261096870914L;
	
	/**
	 * app页面参数Map
	 */
	private Map<String , Object> paramMap = null; // app页面参数
	
	/**
	 * 添加app页面参数
	 * @param index - 参数索引位置
	 * @param value - 参数值
	 */
	public void addParam(int index , Object value){
		if(paramMap == null) paramMap = new HashMap<>();
		paramMap.put("strValue"+index, value);
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	
}
