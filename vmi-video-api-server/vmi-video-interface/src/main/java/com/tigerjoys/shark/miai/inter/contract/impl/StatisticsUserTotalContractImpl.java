package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserTotalContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserTotalEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserTotalMapper;

/**
 * 数据库中  [t_statistics_user_total]表 接口实现类
 * @author chengang
 * @Date 2017-05-26 15:03:43
 *
 */
@Repository
public class StatisticsUserTotalContractImpl extends AbstractBaseContract<StatisticsUserTotalEntity , StatisticsUserTotalMapper> implements IStatisticsUserTotalContract {
	
}
