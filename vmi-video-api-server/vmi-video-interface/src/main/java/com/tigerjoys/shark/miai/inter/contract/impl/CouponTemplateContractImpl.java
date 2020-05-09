package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICouponTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.CouponTemplateEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CouponTemplateMapper;

/**
 * 数据库中  代金券类别[t_coupon_template]表 接口实现类
 * @author yangjunming
 * @Date 2017-09-08 15:30:38
 *
 */
@Repository
public class CouponTemplateContractImpl extends AbstractBaseContract<CouponTemplateEntity , CouponTemplateMapper> implements ICouponTemplateContract {
	
}
