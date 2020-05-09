package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.DeviceBaseStationEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  设备基站信息[t_device_base_station]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-02 14:07:46
 *
 */
@Producer(entityType=DeviceBaseStationEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface DeviceBaseStationMapper extends BaseMapper<DeviceBaseStationEntity> {
    
}