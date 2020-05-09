package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserEnergyRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserEnergyRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserEnergyRechargeMapper;

/**
 * 数据库中  能量充值统计表[t_statistics_user_energy_recharge]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-23 16:55:30
 *
 */
@Repository
public class StatisticsUserEnergyRechargeContractImpl extends AbstractBaseContract<StatisticsUserEnergyRechargeEntity , StatisticsUserEnergyRechargeMapper> implements IStatisticsUserEnergyRechargeContract {
	
}
