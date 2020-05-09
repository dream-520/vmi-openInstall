package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.WorldCupGameEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  世界杯比赛[t_world_cup_game]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-06-15 13:44:51
 *
 */
@Producer(entityType=WorldCupGameEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface WorldCupGameMapper extends BaseMapper<WorldCupGameEntity> {
    
}