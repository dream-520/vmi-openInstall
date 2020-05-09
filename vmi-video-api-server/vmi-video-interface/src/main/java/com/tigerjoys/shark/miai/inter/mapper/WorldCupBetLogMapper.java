package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.WorldCupBetLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  世界杯比赛竞猜记录[t_world_cup_bet_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-06-15 15:49:18
 *
 */
@Producer(entityType=WorldCupBetLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface WorldCupBetLogMapper extends BaseMapper<WorldCupBetLogEntity> {
    
}