package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VipSingleEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  只允许购买一次VIP记录[t_vip_single]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Producer(entityType=VipSingleEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VipSingleMapper extends BaseMapper<VipSingleEntity> {
    
}