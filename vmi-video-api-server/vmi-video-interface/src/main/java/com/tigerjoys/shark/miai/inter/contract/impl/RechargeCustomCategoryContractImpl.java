package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RechargeCustomCategoryMapper;

/**
 * 数据库中  充钻自定义分类表[t_recharge_custom_category]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-03 14:04:38
 *
 */
@Repository
public class RechargeCustomCategoryContractImpl extends AbstractBaseContract<RechargeCustomCategoryEntity , RechargeCustomCategoryMapper> implements IRechargeCustomCategoryContract {
	
	public int updatefirstAll(int first) throws Exception {
		return mapper.updateByStatement("first="+first,"1=1");
	}
}
