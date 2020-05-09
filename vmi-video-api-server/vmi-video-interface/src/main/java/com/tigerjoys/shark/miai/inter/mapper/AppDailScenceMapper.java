package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppDailScenceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_app_dail_scence]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-12-30 11:15:35
 *
 */
@Producer(entityType=AppDailScenceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppDailScenceMapper extends BaseMapper<AppDailScenceEntity> {
    
}