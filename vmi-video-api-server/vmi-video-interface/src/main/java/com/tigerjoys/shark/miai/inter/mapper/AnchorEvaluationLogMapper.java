package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorEvaluationLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播评价日志[t_anchor_evaluation_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2020-02-25 15:02:20
 *
 */
@Producer(entityType=AnchorEvaluationLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorEvaluationLogMapper extends BaseMapper<AnchorEvaluationLogEntity> {
    
}