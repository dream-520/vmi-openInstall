package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppVersionUpgradePopEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  版本升级弹窗表[t_app_version_upgrade_pop]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-09-03 13:21:47
 *
 */
@Producer(entityType=AppVersionUpgradePopEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppVersionUpgradePopMapper extends BaseMapper<AppVersionUpgradePopEntity> {
    
}