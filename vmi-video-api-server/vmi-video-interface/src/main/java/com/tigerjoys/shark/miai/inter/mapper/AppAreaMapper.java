package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppAreaEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  城市字典表[t_app_area]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-05-08 16:12:55
 *
 */
@Producer(entityType=AppAreaEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppAreaMapper extends BaseMapper<AppAreaEntity> {
    
}