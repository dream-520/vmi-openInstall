package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IEnergyCustomPriceContract;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomPriceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.EnergyCustomPriceMapper;

/**
 * 数据库中  充能量自定义价格列表[t_energy_custom_price]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Repository
public class EnergyCustomPriceContractImpl extends AbstractBaseContract<EnergyCustomPriceEntity , EnergyCustomPriceMapper> implements IEnergyCustomPriceContract {
	
}
