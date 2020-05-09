package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 代金券服务类
 * @author yangjunming
 *
 */
public interface ICouponService {
	

	/**
	 * 查询所有代金券
	 * @param userId   
	 * @param stamp      时间戳
	 * @param pageSize   每页记录
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryAllCoupon(long userId) throws Exception;
	
}
