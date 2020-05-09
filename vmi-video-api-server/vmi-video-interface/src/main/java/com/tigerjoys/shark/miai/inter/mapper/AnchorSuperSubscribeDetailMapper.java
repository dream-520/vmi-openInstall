package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeDetailEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户一键预约详情表[t_anchor_super_subscribe_detail]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-11-18 14:45:37
 *
 */
@Producer(entityType=AnchorSuperSubscribeDetailEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorSuperSubscribeDetailMapper extends BaseMapper<AnchorSuperSubscribeDetailEntity> {
    
}