package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TalentLevelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  达人等级表[t_talent_level]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2018-01-29 14:20:07
 *
 */
@Producer(entityType=TalentLevelEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface TalentLevelMapper extends BaseMapper<TalentLevelEntity> {
    
}