package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.IosDistributionPlatformEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  IOS分发平台[t_ios_distribution_platform]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-04-02 15:15:02
 *
 */
@Producer(entityType=IosDistributionPlatformEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface IosDistributionPlatformMapper extends BaseMapper<IosDistributionPlatformEntity> {
    
}