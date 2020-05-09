package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RouletteWheelLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  轮盘抽奖记录[t_roulette_wheel_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-07-25 16:40:39
 *
 */
@Producer(entityType=RouletteWheelLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface RouletteWheelLogMapper extends BaseMapper<RouletteWheelLogEntity> {
    
}