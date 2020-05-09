package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineTimeStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播在线情况统计表[t_anchor_online_time_statistics]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-02 16:39:45
 *
 */
@Producer(entityType=AnchorOnlineTimeStatisticsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorOnlineTimeStatisticsMapper extends BaseMapper<AnchorOnlineTimeStatisticsEntity> {
    
}