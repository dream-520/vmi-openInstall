package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserPayAnalysisEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户充值行为分析表[t_statistics_user_pay_analysis]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-02 14:31:06
 *
 */
@Producer(entityType=StatisticsUserPayAnalysisEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsUserPayAnalysisMapper extends BaseMapper<StatisticsUserPayAnalysisEntity> {
    
}