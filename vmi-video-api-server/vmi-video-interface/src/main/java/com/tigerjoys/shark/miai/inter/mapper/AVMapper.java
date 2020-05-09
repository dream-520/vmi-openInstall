package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AVEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_a_v]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-06 16:11:06
 *
 */
@Producer(entityType=AVEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AVMapper extends BaseMapper<AVEntity> {
    
}