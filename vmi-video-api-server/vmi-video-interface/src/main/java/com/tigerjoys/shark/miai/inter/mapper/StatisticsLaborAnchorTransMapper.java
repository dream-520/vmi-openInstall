package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsLaborAnchorTransEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  工会主播汇总统计[t_statistics_labor_anchor_trans]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-06 16:33:01
 *
 */
@Producer(entityType=StatisticsLaborAnchorTransEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsLaborAnchorTransMapper extends BaseMapper<StatisticsLaborAnchorTransEntity> {
    
}