package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsPlatformAllowanceContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsPlatformAllowanceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsPlatformAllowanceMapper;

/**
 * 数据库中  平台渠道每日补助统计[t_statistics_platform_allowance]表 接口实现类
 * @author shiming
 * @Date 2019-07-01 16:22:15
 *
 */
@Repository
public class StatisticsPlatformAllowanceContractImpl extends AbstractBaseContract<StatisticsPlatformAllowanceEntity , StatisticsPlatformAllowanceMapper> implements IStatisticsPlatformAllowanceContract {
	
}
