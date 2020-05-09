package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserPayContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserPayEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserPayMapper;

/**
 * 数据库中  充值用户每日数据统计[t_statistics_user_pay]表 接口实现类
 * @author lipeng
 * @Date 2019-06-24 20:42:39
 *
 */
@Repository
public class StatisticsUserPayContractImpl extends AbstractBaseContract<StatisticsUserPayEntity , StatisticsUserPayMapper> implements IStatisticsUserPayContract {
	
}
