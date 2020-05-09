package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  充值统计表[t_statistics_user_recharge]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-22 10:45:32
 *
 */
@Producer(entityType=StatisticsUserRechargeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsUserRechargeMapper extends BaseMapper<StatisticsUserRechargeEntity> {
    
}