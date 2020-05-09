package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorContactEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播对应的联系方式信息[t_anchor_contact]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-01-04 19:55:17
 *
 */
@Producer(entityType=AnchorContactEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorContactMapper extends BaseMapper<AnchorContactEntity> {
    
}