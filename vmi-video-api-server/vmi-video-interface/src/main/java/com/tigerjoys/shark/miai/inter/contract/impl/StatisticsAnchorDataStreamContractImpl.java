package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsAnchorDataStreamContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorDataStreamEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsAnchorDataStreamMapper;

/**
 * 数据库中  主播数据流统计[t_statistics_anchor_data_stream]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-06 16:48:30
 *
 */
@Repository
public class StatisticsAnchorDataStreamContractImpl extends AbstractBaseContract<StatisticsAnchorDataStreamEntity , StatisticsAnchorDataStreamMapper> implements IStatisticsAnchorDataStreamContract {
	
}
