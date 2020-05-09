package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppAreaCityEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  城市级别对应表[t_app_area_city]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-26 14:49:39
 *
 */
@Producer(entityType=AppAreaCityEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppAreaCityMapper extends BaseMapper<AppAreaCityEntity> {
    
}