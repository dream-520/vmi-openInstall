package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AaPdEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_aa_pd]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-02 09:55:28
 *
 */
@Producer(entityType=AaPdEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AaPdMapper extends BaseMapper<AaPdEntity> {
    
}