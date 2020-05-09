package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsAnchorTransUserPayIncomeContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorTransUserPayIncomeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsAnchorTransUserPayIncomeMapper;

/**
 * 数据库中  [t_statistics_anchor_trans_user_pay_income]表 接口实现类
 * @author shiming
 * @Date 2019-07-23 15:32:31
 *
 */
@Repository
public class StatisticsAnchorTransUserPayIncomeContractImpl extends AbstractBaseContract<StatisticsAnchorTransUserPayIncomeEntity , StatisticsAnchorTransUserPayIncomeMapper> implements IStatisticsAnchorTransUserPayIncomeContract {
	
}
