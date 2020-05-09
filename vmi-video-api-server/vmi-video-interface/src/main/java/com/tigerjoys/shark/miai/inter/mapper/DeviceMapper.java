package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.DeviceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  设备表[t_device]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-08-08 14:51:52
 *
 */
@Producer(entityType=DeviceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface DeviceMapper extends BaseMapper<DeviceEntity> {
    
}