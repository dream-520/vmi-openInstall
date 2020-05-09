package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  购买守户和VIP[t_guard_vip_category]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-04 20:27:15
 *
 */
@Producer(entityType=GuardVipCategoryEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface GuardVipCategoryMapper extends BaseMapper<GuardVipCategoryEntity> {
    
}