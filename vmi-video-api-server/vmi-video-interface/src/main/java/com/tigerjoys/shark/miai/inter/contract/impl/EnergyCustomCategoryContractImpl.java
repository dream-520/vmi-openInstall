package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IEnergyCustomCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.EnergyCustomCategoryMapper;

/**
 * 数据库中  充能量自定义分类表[t_energy_custom_category]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Repository
public class EnergyCustomCategoryContractImpl extends AbstractBaseContract<EnergyCustomCategoryEntity , EnergyCustomCategoryMapper> implements IEnergyCustomCategoryContract {
	
}
