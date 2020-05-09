package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppIosDeviceUdidEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  ios设备udid[t_app_ios_device_udid]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-23 09:46:08
 *
 */
@Producer(entityType=AppIosDeviceUdidEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppIosDeviceUdidMapper extends BaseMapper<AppIosDeviceUdidEntity> {
    
}