package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AMDayTimesEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_a_m_day_times]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-15 14:03:19
 *
 */
@Producer(entityType=AMDayTimesEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AMDayTimesMapper extends BaseMapper<AMDayTimesEntity> {
    
}