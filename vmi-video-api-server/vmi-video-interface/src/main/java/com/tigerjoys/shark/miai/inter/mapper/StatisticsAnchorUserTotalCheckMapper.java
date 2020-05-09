package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorUserTotalCheckEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播和用户汇总统计[t_statistics_anchor_user_total_check]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-06 16:48:30
 *
 */
@Producer(entityType=StatisticsAnchorUserTotalCheckEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsAnchorUserTotalCheckMapper extends BaseMapper<StatisticsAnchorUserTotalCheckEntity> {
    
}