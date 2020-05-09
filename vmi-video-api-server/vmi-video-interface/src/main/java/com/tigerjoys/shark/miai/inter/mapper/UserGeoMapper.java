package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserGeoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户定位信息表[t_user_geo]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-06-02 12:25:24
 *
 */
@Producer(entityType=UserGeoEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserGeoMapper extends BaseMapper<UserGeoEntity> {
    
}