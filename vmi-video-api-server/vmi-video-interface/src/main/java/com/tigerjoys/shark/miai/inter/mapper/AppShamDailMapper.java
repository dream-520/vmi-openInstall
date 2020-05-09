package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppShamDailEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  虚假来电弹窗[t_app_sham_dail]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-17 17:02:19
 *
 */
@Producer(entityType=AppShamDailEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppShamDailMapper extends BaseMapper<AppShamDailEntity> {
    
}