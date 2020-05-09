package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsCpsShareDailyContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsCpsShareDailyEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsCpsShareDailyMapper;

/**
 * 数据库中  蜜聊CPS每日邀请统计[t_statistics_cps_share_daily]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-23 19:46:51
 *
 */
@Repository
public class StatisticsCpsShareDailyContractImpl extends AbstractBaseContract<StatisticsCpsShareDailyEntity , StatisticsCpsShareDailyMapper> implements IStatisticsCpsShareDailyContract {
	
}
