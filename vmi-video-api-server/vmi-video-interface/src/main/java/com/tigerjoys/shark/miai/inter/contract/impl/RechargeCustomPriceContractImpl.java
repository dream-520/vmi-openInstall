package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomPriceContract;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomPriceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RechargeCustomPriceMapper;

/**
 * 数据库中  充钻自定义价格列表[t_recharge_custom_price]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-03 14:04:38
 *
 */
@Repository
public class RechargeCustomPriceContractImpl extends AbstractBaseContract<RechargeCustomPriceEntity , RechargeCustomPriceMapper> implements IRechargeCustomPriceContract {
	
}
