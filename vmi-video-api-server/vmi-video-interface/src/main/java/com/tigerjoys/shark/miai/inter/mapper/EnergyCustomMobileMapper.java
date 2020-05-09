package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomMobileEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  充能量自定义手机型号表[t_energy_custom_mobile]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Producer(entityType=EnergyCustomMobileEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface EnergyCustomMobileMapper extends BaseMapper<EnergyCustomMobileEntity> {
    
}