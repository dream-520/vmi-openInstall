package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TalentVipEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  达人VIP表[t_talent_vip]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-08-21 08:55:32
 *
 */
@Producer(entityType=TalentVipEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface TalentVipMapper extends BaseMapper<TalentVipEntity> {
    
}