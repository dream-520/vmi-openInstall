package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsPayActUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  付费用户活跃表[t_statistics_pay_act_user]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-11-27 19:56:54
 *
 */
@Producer(entityType=StatisticsPayActUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsPayActUserMapper extends BaseMapper<StatisticsPayActUserEntity> {
    
}