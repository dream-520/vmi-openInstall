package com.tigerjoys.shark.miai.service;

import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;

/**
 * 领取商品接口类
 * @author lipeng
 *
 */
public interface ICommodityService {
	

	/**
	 * 领取商品接口列表
	 * @param model
	 */
	public void getCommodityList(Model model)throws Exception;

	
	/**
	 * 领取商品接口列表
	 * @param model
	 */
	public void getRecord(Model model)throws Exception;


	/**
	 * 礼包内商品列表
	 * @param group_id
	 * @param model
	 * @throws Exception
	 */
	public void getCommodityListOfGroup(long group_id, Model model)throws Exception;


	/**
	 * 领取商品
	 * @param id
	 * @param relationship_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject geting(long id, long relationship_id)throws Exception;
	
	

}
