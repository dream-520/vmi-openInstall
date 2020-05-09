package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorContactLookEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户查看主播联系方式记录表[t_anchor_contact_look]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-01-06 16:50:09
 *
 */
@Producer(entityType=AnchorContactLookEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorContactLookMapper extends BaseMapper<AnchorContactLookEntity> {
    
}