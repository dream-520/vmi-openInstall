package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  充钻自定义分类表[t_recharge_custom_category]表 接口类
 * @author mouzhanpeng
 * @Date 2018-08-03 14:04:38
 *
 */
public interface IRechargeCustomCategoryContract extends BaseContract<RechargeCustomCategoryEntity> {
	
	public int updatefirstAll(int first) throws Exception ;
	
}
