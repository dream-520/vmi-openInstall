package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomMobileContract;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomMobileEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RechargeCustomMobileMapper;

/**
 * 数据库中  充钻自定义手机型号表[t_recharge_custom_mobile]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-03 14:04:38
 *
 */
@Repository
public class RechargeCustomMobileContractImpl extends AbstractBaseContract<RechargeCustomMobileEntity , RechargeCustomMobileMapper> implements IRechargeCustomMobileContract {
	
}
