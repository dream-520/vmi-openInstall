package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsPlatformAllowanceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  平台渠道每日补助统计[t_statistics_platform_allowance]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-01 16:22:15
 *
 */
@Producer(entityType=StatisticsPlatformAllowanceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsPlatformAllowanceMapper extends BaseMapper<StatisticsPlatformAllowanceEntity> {
    
}