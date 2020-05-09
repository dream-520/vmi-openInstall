package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserCallAnchorCheckContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserCallAnchorCheckEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserCallAnchorCheckMapper;

/**
 * 数据库中  用户拨打主播统计[t_statistics_user_call_anchor_check]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-06 16:48:30
 *
 */
@Repository
public class StatisticsUserCallAnchorCheckContractImpl extends AbstractBaseContract<StatisticsUserCallAnchorCheckEntity , StatisticsUserCallAnchorCheckMapper> implements IStatisticsUserCallAnchorCheckContract {
	
}
