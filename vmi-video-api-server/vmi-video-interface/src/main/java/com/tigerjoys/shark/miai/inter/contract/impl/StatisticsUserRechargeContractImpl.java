package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserRechargeMapper;

/**
 * 数据库中  充值统计表[t_statistics_user_recharge]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-22 10:45:32
 *
 */
@Repository
public class StatisticsUserRechargeContractImpl extends AbstractBaseContract<StatisticsUserRechargeEntity , StatisticsUserRechargeMapper> implements IStatisticsUserRechargeContract {
	
}
