package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IEnergyCustomMobileContract;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomMobileEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.EnergyCustomMobileMapper;

/**
 * 数据库中  充能量自定义手机型号表[t_energy_custom_mobile]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Repository
public class EnergyCustomMobileContractImpl extends AbstractBaseContract<EnergyCustomMobileEntity , EnergyCustomMobileMapper> implements IEnergyCustomMobileContract {
	
}
