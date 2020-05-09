package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserEnergyAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户能量账户流水[t_user_energy_account_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-17 14:31:33
 *
 */
@Producer(entityType=UserEnergyAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserEnergyAccountLogMapper extends BaseMapper<UserEnergyAccountLogEntity> {
    
}