package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.GlobalBroadcastVipEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  全局广播[t_global_broadcast_vip]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-12-03 18:10:56
 *
 */
@Producer(entityType=GlobalBroadcastVipEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface GlobalBroadcastVipMapper extends BaseMapper<GlobalBroadcastVipEntity> {
    
}