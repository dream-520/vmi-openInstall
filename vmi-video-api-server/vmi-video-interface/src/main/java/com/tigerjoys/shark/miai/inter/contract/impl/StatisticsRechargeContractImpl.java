package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsRechargeMapper;

/**
 * 数据库中  [t_statistics_recharge]表 接口实现类
 * @author shiming
 * @Date 2018-12-25 17:03:47
 *
 */
@Repository
public class StatisticsRechargeContractImpl extends AbstractBaseContract<StatisticsRechargeEntity , StatisticsRechargeMapper> implements IStatisticsRechargeContract {
	
}
