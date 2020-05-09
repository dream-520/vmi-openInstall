package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  首页弹窗log表[t_home_pop_log]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-12-21 18:21:58
 *
 */
@Producer(entityType=HomePopLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface HomePopLogMapper extends BaseMapper<HomePopLogEntity> {
    
}