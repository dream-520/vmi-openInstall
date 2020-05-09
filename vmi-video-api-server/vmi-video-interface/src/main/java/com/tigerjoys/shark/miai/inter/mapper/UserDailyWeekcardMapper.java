package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDailyWeekcardEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户每日周卡[t_user_daily_weekcard]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-11-16 11:01:30
 *
 */
@Producer(entityType=UserDailyWeekcardEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserDailyWeekcardMapper extends BaseMapper<UserDailyWeekcardEntity> {
    
}