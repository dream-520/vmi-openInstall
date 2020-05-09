package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserDataStreamContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserDataStreamEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserDataStreamMapper;

/**
 * 数据库中  用户数据流统计[t_statistics_user_data_stream]表 接口实现类
 * @author lipeng
 * @Date 2019-04-12 10:38:32
 *
 */
@Repository
public class StatisticsUserDataStreamContractImpl extends AbstractBaseContract<StatisticsUserDataStreamEntity , StatisticsUserDataStreamMapper> implements IStatisticsUserDataStreamContract {
	
}
