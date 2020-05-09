package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppChannelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  渠道信息表[t_app_channel]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-05-17 16:04:19
 *
 */
@Producer(entityType=AppChannelEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppChannelMapper extends BaseMapper<AppChannelEntity> {
    
}