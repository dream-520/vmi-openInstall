package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AMDayTotalEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_a_m_day_total]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-15 14:03:19
 *
 */
@Producer(entityType=AMDayTotalEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AMDayTotalMapper extends BaseMapper<AMDayTotalEntity> {
    
}