package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息跳转页面参数dto
 * 
 * @author liuman
 *
 */
public class MessageParamDto implements Serializable {
	
	private static final long serialVersionUID = -8820201178299003278L;
	
	/**
	 * app页面参数
	 */
	private Map<String , Object> param = null; // app页面参数
	
	
	/**
	 * 添加app页面参数
	 * @param index - 参数索引位置
	 * @param value - 参数值
	 */
	public void addParam(int index , Object value){
		if(param == null) param = new HashMap<>();
		param.put("strValue"+index, value);
	}


	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

}
