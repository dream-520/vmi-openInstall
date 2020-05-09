package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AMDayIncomeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_a_m_day_income]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-15 17:19:33
 *
 */
@Producer(entityType=AMDayIncomeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AMDayIncomeMapper extends BaseMapper<AMDayIncomeEntity> {
    
}