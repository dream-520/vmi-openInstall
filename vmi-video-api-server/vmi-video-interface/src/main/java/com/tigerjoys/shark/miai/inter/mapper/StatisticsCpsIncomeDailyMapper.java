package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsCpsIncomeDailyEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  蜜聊CPS每日收益统计[t_statistics_cps_income_daily]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-23 19:46:51
 *
 */
@Producer(entityType=StatisticsCpsIncomeDailyEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsCpsIncomeDailyMapper extends BaseMapper<StatisticsCpsIncomeDailyEntity> {
    
}