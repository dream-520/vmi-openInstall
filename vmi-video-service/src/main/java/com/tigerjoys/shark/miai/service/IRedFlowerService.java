package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/** 
  * @author mouzhanpeng at [2017年12月20日 下午3:49:57] 
  * @since JDK 1.8.0 
  */
public interface IRedFlowerService {

	/**
	 * 小红花首页
	 * @return
	 * @throws Exception
	 */
	public ActionResult redFlowerHome() throws Exception;
	
	/**
	 * 钻石购买小红花
	 * @param flowerId
	 * @return
	 * @throws Exception
	 */
	public ActionResult buyRedFlower(long flowerId) throws Exception;
	
	/**
	 * 赠送小红花
	 * @param userId
	 * @throws Exception
	 */
	public void donorRedFlowers(long userId) throws Exception;
	
	/**
	 * 消费小红花
	 * @param otherId
	 * @return
	 * @throws Exception
	 */
	public ActionResult consumeRedFlower(long otherId) throws Exception;

}
