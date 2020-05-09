package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_coupon]表 接口类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
public interface ICouponContract extends BaseContract<CouponEntity> {
	/**
	 * 代金券使用
	 * @param id
	 * @param orderId
	 * @return   0未使用成功  1 使用成功
	 * @throws Exception
	 */
	 
	public int useCoupon(long id,long orderId) throws Exception;
	
	/**
	 * 恢复代金券为可使用状态
	 * @param id - 代金券ID
	 * @throws Exception
	 */
	public void recoverCoupon(long id) throws Exception;
	
	/**
	 * 删除代金券
	 * 代金券使用
	 * @param id
	 * @throws Exception
	 */
	 
	public int deleteCoupon(long id) throws Exception;
}
