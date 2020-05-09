package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.ICouponContract;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.shark.miai.inter.mapper.CouponMapper;

/**
 * 数据库中  [t_coupon]表 接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Repository
public class CouponContractImpl extends AbstractBaseContract<CouponEntity , CouponMapper> implements ICouponContract {
	@Override
	public int useCoupon(long id, long orderId) throws Exception {
		int i = mapper.updateByStatement("status=1,orderid="+orderId+",use_time=now()", "id="+id+" and status=0");
		if(i <= 0) {
			throw new IllegalAccessError("coupon is used!");
		}
		
		return i;
	}
	
	@Override
	public void recoverCoupon(long id) throws Exception {
		int i = mapper.updateByStatement("status=0", "id="+id+" and status=1");
		if(i <= 0) {
			throw new IllegalAccessError("coupon is unused!");
		}
	}

	@Override
	public int deleteCoupon(long id) throws Exception {
		
		return  mapper.updateByStatement("status=-9", "id="+id);
	}
}
