package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserVchatDataContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserVchatDataEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserVchatDataMapper;

/**
 * 数据库中  用户聊天数据统计[t_statistics_user_vchat_data]表 接口实现类
 * @author lipeng
 * @Date 2019-07-02 17:27:56
 *
 */
@Repository
public class StatisticsUserVchatDataContractImpl extends AbstractBaseContract<StatisticsUserVchatDataEntity , StatisticsUserVchatDataMapper> implements IStatisticsUserVchatDataContract {
	
}
