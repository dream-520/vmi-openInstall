package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.HotCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  首页热门管理[t_hot_category]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2018-01-25 17:50:29
 *
 */
@Producer(entityType=HotCategoryEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface HotCategoryMapper extends BaseMapper<HotCategoryEntity> {
    
}