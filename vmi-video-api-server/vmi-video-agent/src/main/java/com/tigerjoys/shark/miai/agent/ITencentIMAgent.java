/**
 * 
 */
package com.tigerjoys.shark.miai.agent;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 腾讯云接口
 * @see https://cloud.tencent.com/document/product/269/2660
 * @author yangjunming
 */
public interface ITencentIMAgent {

	/**
	 * 通信接口调用
	 * 
	 * @param api
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public JSONObject requestData(String api, String params) throws Exception;
	
	/**
	 * 用户注册
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject createUser(String params) throws Exception;

	
}
