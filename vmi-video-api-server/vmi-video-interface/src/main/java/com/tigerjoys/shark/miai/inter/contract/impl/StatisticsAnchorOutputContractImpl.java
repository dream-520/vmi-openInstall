package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsAnchorOutputContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorOutputEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsAnchorOutputMapper;

/**
 * 数据库中  付费用户表[t_statistics_anchor_output]表 接口实现类
 * @author lipeng
 * @Date 2019-10-18 17:42:58
 *
 */
@Repository
public class StatisticsAnchorOutputContractImpl extends AbstractBaseContract<StatisticsAnchorOutputEntity , StatisticsAnchorOutputMapper> implements IStatisticsAnchorOutputContract {
	
}
