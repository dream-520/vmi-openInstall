package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户一键预约表[t_anchor_super_subscribe_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-11-18 11:22:47
 *
 */
@Producer(entityType=AnchorSuperSubscribeUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorSuperSubscribeUserMapper extends BaseMapper<AnchorSuperSubscribeUserEntity> {
    
}