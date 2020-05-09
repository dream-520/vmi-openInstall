package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorTimeStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播每日在线统计表[t_anchor_time_statistics]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-04-16 14:43:20
 *
 */
@Producer(entityType=AnchorTimeStatisticsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorTimeStatisticsMapper extends BaseMapper<AnchorTimeStatisticsEntity> {
    
}