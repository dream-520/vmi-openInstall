package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsAnchorUserTotalCheckContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorUserTotalCheckEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsAnchorUserTotalCheckMapper;

/**
 * 数据库中  主播和用户汇总统计[t_statistics_anchor_user_total_check]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-06 16:48:30
 *
 */
@Repository
public class StatisticsAnchorUserTotalCheckContractImpl extends AbstractBaseContract<StatisticsAnchorUserTotalCheckEntity , StatisticsAnchorUserTotalCheckMapper> implements IStatisticsAnchorUserTotalCheckContract {
	
}
