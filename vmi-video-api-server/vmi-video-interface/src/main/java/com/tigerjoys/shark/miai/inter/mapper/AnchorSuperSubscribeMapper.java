package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  金牌预约大V[t_anchor_super_subscribe]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-11-16 18:52:02
 *
 */
@Producer(entityType=AnchorSuperSubscribeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorSuperSubscribeMapper extends BaseMapper<AnchorSuperSubscribeEntity> {
    
}