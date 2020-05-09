package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 转盘服务类
 * @author yangjunming
 *
 */
public interface IVchatTurntableService {

	/**
	 * 转盘首页
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ActionResult homeInfo(long userId, Long otherId) throws Exception;
	
	
	/**
	 * 获取中奖信息
	 * @param userId
	 * @param otherId
	 * @return
	 * @throws Exception
	 */
	public ActionResult turntableInfo(long userId, Long otherId) throws Exception;
	
	/**
	 * 支付中奖信息
	 * @param userId
	 * @param otherId
	 * @return
	 * @throws Exception
	 */
	public ActionResult turntablePay(String orderId) throws Exception ;
	
	
}
