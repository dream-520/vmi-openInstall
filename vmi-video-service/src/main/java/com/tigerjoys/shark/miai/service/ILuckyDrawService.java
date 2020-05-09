package com.tigerjoys.shark.miai.service;

import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;

/**
 * APP幸运抽奖接口类
 * @author lipeng
 *
 */
public interface ILuckyDrawService {
	

	/**
	 * 初始化活动数据
	 * @param model
	 */
	public void initLunPan(Model model)throws Exception;

	/**
	 * 提交抽奖结果
	 * @return
	 * @throws Exception
	 */
	public JSONObject commitLunPan()throws Exception;


}
