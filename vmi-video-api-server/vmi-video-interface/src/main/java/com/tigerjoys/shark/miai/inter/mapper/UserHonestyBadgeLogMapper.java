package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户诚信徽章充值记录[t_user_honesty_badge_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
@Producer(entityType=UserHonestyBadgeLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserHonestyBadgeLogMapper extends BaseMapper<UserHonestyBadgeLogEntity> {
    
}