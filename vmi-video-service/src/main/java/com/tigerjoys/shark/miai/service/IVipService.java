package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * VIP服务类
 * @author yangjunming
 *
 */
public interface IVipService {

	/**
	 * 查询代金券
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryVip(long userId) throws Exception;
	
	/**
	 * 钻石购买VIP
	 * @param userId
	 * @param orderId	订单ID
	 * @param vipId		VIP类型ID
	 * @param diamond
	 * @return
	 * @throws Exception
	 */
	public ActionResult buyVip(long userId,String orderId,long vipId,int diamond) throws Exception;

	/**
	 * 能量购买VIP
	 * @param userId
	 * @param orderId	订单ID
	 * @param vipId		VIP类型ID
	 * @param diamond
	 * @return
	 * @throws Exception
	 */
	public ActionResult buyVipUsedEnergy(long userid, String orderId, long vipId, int energy) throws Exception ;
	
}
