package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorSubscribeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_anchor_subscribe]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-11-11 14:01:46
 *
 */
@Producer(entityType=AnchorSubscribeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorSubscribeMapper extends BaseMapper<AnchorSubscribeEntity> {
    
}