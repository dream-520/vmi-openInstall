package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppPackageEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  包名信息表[t_app_package]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-28 10:30:02
 *
 */
@Producer(entityType=AppPackageEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppPackageMapper extends BaseMapper<AppPackageEntity> {
    
}