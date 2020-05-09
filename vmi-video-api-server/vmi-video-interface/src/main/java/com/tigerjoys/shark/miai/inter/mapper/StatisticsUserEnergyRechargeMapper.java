package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserEnergyRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  能量充值统计表[t_statistics_user_energy_recharge]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-23 16:55:30
 *
 */
@Producer(entityType=StatisticsUserEnergyRechargeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsUserEnergyRechargeMapper extends BaseMapper<StatisticsUserEnergyRechargeEntity> {
    
}