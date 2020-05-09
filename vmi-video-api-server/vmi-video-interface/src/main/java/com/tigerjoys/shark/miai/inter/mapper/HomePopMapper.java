package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.HomePopEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  首页弹窗功能表[t_home_pop]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-12-20 14:12:24
 *
 */
@Producer(entityType=HomePopEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface HomePopMapper extends BaseMapper<HomePopEntity> {
    
}