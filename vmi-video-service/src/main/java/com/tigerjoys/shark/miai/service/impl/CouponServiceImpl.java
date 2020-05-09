package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.dto.service.CouponDto;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.shark.miai.service.ICouponService;
/**
 * 代金券服务类
 * @author yangjunming
 *
 */
@Service
public class CouponServiceImpl implements ICouponService{
	
	@Autowired
	private ICouponAgent couponAgent;
	
	@Override
	public ActionResult queryAllCoupon(long userId) throws Exception{
		List<CouponEntity> list=couponAgent.findByUserId(userId);
		List<CouponDto>couponList=new ArrayList<CouponDto>();
		if(Tools.isNotNull(list)){
			for(CouponEntity dto:list){
				couponList.add(CouponDto.pareDto(dto));
			}
		}
		return ActionResult.success(couponList);
	}

}
