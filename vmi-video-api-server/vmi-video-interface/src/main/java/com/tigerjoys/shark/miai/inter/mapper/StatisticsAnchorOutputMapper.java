package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorOutputEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  付费用户表[t_statistics_anchor_output]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-10-18 17:42:58
 *
 */
@Producer(entityType=StatisticsAnchorOutputEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsAnchorOutputMapper extends BaseMapper<StatisticsAnchorOutputEntity> {
    
}