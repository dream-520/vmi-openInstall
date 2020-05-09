package com.tigerjoys.shark.miai.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.IErrorCodeEnum;
import com.tigerjoys.nbs.common.utils.JsonHelper;

public class JsonpActionResult implements Serializable {

	//返回码
	private int code;
	//返回描述
	private String codemsg;
	//返回的数据
	private Object data;
	//分页戳
	private Object stamp;
	//是否有下一页
	private Boolean nextPage;
	
	//需要返回的callback方法
	private String callback;
	
	/**
	 * @param code - 返回码
	 * @param codemsg - 返回描述
	 * @param data - 返回的数据
	 * @param stamp - 分页戳
	 * @param nextPage - 是否有下一页
	 * @return String
	 */
	public static String getResult(String callback, int code , String codemsg , Object data , Object stamp, Boolean nextPage){
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("codemsg", codemsg);
		map.put("data", data);
		map.put("stamp", stamp);
		map.put("nextPage", nextPage);
		String json = JsonHelper.toJson(map);
		return callback + "(" + json + ")";
	}
	
	/**
	 * @param code - 返回码
	 * @param codemsg - 返回描述
	 * @param data - 返回的数据
	 * @param stamp - 分页戳
	 * @return String
	 */
	public static String getResult(String callback, int code , String codemsg , Object data){
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("codemsg", codemsg);
		map.put("data", data);
		String json = JsonHelper.toJson(map);
		return callback + "(" + json + ")";
	}
	
	/**
	 * 返回成功
	 * @return ActionResult
	 */
	public static String success(String callback) {
		return success(callback, null , null , null);
	}
	
	/**
	 * 返回成功
	 * @param data - 返回的数据
	 */
	public static String success(String callback, Object data) {
		return success(callback, data , null , null);
	}
	
	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @param successMsg - 自定义文案
	 */
	public static String success(String callback, Object data,String successMsg) {
		 return success(callback, data, successMsg, null);
	}
	
	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @param stamp - 分页戳
	 * @param nextPage - 是否有下一页
	 */
	public static String success(String callback, Object data , Object stamp , Boolean nextPage){
		return getResult(callback, 0, "成功" , data , stamp, nextPage);
	}
	
	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @param successMsg - 自定义文案
	 * @param stamp - 分页戳
	 * @param nextPage - 是否有下一页
	 */
	public static String success(String callback, Object data , String successMsg , Object stamp , Boolean nextPage){
		return getResult(callback, 0, successMsg, data, stamp, nextPage);
	}
	
	/**
	 * 返回默认的错误失败
	 * @return - ActionResult
	 */
	public static String fail(String callback) {
		return getResult(callback, 1, "系统异常", null);
	}
	
	/**
	 * 返回错误信息
	 * @param errorCode - 错误枚举
	 */
	public static String fail(String callback, IErrorCodeEnum errorCode) {
		return getResult(callback, errorCode.getCode(), errorCode.getDesc(), null);
	}
	
	/**
	 * 返回失败
	 * @param code - 返回码
	 * @param codemsg - 返回描述
	 */
	public static String fail(String callback, int code , String codemsg) {
		return getResult(callback, code , codemsg , null);
	}
	
	/**
	 * 返回失败
	 * @param code - 返回码
	 * @param codemsg - 返回描述
	 * @param data - 返回的数据
	 */
	public static String fail(String callback, int code , String codemsg , Object data) {
		return getResult(callback, code , codemsg , data);
	}
		
}
