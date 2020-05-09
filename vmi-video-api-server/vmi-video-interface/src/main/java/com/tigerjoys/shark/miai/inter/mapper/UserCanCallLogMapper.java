package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserCanCallLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  可拨打用户记录表[t_user_can_call_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-10-14 18:25:51
 *
 */
@Producer(entityType=UserCanCallLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserCanCallLogMapper extends BaseMapper<UserCanCallLogEntity> {
    
}