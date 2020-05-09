package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.GlobalBroadcastEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  全局广播[t_global_broadcast]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-01-08 10:35:57
 *
 */
@Producer(entityType=GlobalBroadcastEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface GlobalBroadcastMapper extends BaseMapper<GlobalBroadcastEntity> {
    
}