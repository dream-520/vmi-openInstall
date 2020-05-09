package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_statistics_recharge]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-12-25 17:03:47
 *
 */
@Producer(entityType=StatisticsRechargeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsRechargeMapper extends BaseMapper<StatisticsRechargeEntity> {
    
}