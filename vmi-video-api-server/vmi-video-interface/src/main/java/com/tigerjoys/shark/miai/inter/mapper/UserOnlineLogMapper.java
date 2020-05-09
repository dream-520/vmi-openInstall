package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserOnlineLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户在线记录表[t_user_online_log]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-05-10 18:20:19
 *
 */
@Producer(entityType=UserOnlineLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserOnlineLogMapper extends BaseMapper<UserOnlineLogEntity> {
    
}