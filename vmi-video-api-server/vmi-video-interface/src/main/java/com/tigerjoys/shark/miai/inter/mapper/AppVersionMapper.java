package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppVersionEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  版本信息表[t_app_version]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-05-17 16:00:28
 *
 */
@Producer(entityType=AppVersionEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppVersionMapper extends BaseMapper<AppVersionEntity> {
    
}