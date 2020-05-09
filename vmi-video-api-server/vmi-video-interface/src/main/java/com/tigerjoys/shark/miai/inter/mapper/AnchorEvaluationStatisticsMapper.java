package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorEvaluationStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播评价统计[t_anchor_evaluation_statistics]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-11-05 18:14:49
 *
 */
@Producer(entityType=AnchorEvaluationStatisticsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorEvaluationStatisticsMapper extends BaseMapper<AnchorEvaluationStatisticsEntity> {
    
}