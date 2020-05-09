package com.tigerjoys.shark.miai.service;

import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;

/**
 * APP幸运抽奖接口类
 * @author lipeng
 *
 */
public interface IUserSignService {
	

	/**
	 * 初始化签到页面
	 * @param model
	 */
	public void initSign(Model model)throws Exception;

	/**
	 * 签到
	 * @return
	 * @throws Exception
	 */
	public JSONObject signing()throws Exception;
	
	/**
	 * 签到
	 * @return
	 * @throws Exception
	 */
	public JSONObject buySignCard()throws Exception;

	/**
	 * 补签页面
	 * @return
	 * @throws Exception
	 */
	public void supplement(Model model)throws Exception;
	
	/**
	 * 签到日历初始化
	 * @return
	 * @throws Exception
	 */
	public JSONObject calendarInit()throws Exception;

	/**
	 * 补签
	 * @return
	 * @throws Exception
	 */
	public JSONObject suppleDays(String day)throws Exception;
	

}
