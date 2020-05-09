package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppControllEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  审核数据控制[t_app_controll]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-06-22 15:12:52
 *
 */
@Producer(entityType=AppControllEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppControllMapper extends BaseMapper<AppControllEntity> {
    
}