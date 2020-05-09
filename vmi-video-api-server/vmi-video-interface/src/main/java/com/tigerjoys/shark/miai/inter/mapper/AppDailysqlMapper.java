package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppDailysqlEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  每日SQL数据[t_app_dailysql]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-25 15:33:24
 *
 */
@Producer(entityType=AppDailysqlEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppDailysqlMapper extends BaseMapper<AppDailysqlEntity> {
    
}