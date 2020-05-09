package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * APP幸运抽奖接口类
 * @author lipeng
 *
 */
public interface IUserSignTaskService {
	

	/**
	 * 初始化签到页面
	 * @param model
	 */
	public ActionResult initSign(long userid)throws Exception;

	/**
	 * 签到
	 * @return
	 * @throws Exception
	 */
	public ActionResult signing(long userid)throws Exception;

	/**
	 * 积分兑换
	 * @param point
	 * @param type
	 * @return 
	 * @throws Exception
	 */
	public ActionResult pointExchanging(long point, int type)throws Exception;
	

}
