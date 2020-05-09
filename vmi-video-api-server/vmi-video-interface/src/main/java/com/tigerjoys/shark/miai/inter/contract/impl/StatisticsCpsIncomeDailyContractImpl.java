package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsCpsIncomeDailyContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsCpsIncomeDailyEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsCpsIncomeDailyMapper;

/**
 * 数据库中  蜜聊CPS每日收益统计[t_statistics_cps_income_daily]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-23 19:46:51
 *
 */
@Repository
public class StatisticsCpsIncomeDailyContractImpl extends AbstractBaseContract<StatisticsCpsIncomeDailyEntity , StatisticsCpsIncomeDailyMapper> implements IStatisticsCpsIncomeDailyContract {
	
}
