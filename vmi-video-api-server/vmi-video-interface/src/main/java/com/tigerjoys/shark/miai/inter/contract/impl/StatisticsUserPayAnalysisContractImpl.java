package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsUserPayAnalysisContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserPayAnalysisEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsUserPayAnalysisMapper;

/**
 * 数据库中  用户充值行为分析表[t_statistics_user_pay_analysis]表 接口实现类
 * @author shiming
 * @Date 2019-07-02 14:31:06
 *
 */
@Repository
public class StatisticsUserPayAnalysisContractImpl extends AbstractBaseContract<StatisticsUserPayAnalysisEntity , StatisticsUserPayAnalysisMapper> implements IStatisticsUserPayAnalysisContract {
	
}
