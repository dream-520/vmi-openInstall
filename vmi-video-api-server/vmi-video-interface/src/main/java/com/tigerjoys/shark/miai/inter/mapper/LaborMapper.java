package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.LaborEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  工会列表[t_labor]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-09-21 11:27:53
 *
 */
@Producer(entityType=LaborEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface LaborMapper extends BaseMapper<LaborEntity> {
    
}