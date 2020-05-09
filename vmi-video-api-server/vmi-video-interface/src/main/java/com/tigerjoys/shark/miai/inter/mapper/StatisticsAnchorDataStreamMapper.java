package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorDataStreamEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播数据流统计[t_statistics_anchor_data_stream]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-06 16:48:30
 *
 */
@Producer(entityType=StatisticsAnchorDataStreamEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsAnchorDataStreamMapper extends BaseMapper<StatisticsAnchorDataStreamEntity> {
    
}