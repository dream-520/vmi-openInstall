package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsAddLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用于记录是否添加过好友关系表[t_user_friends_add_log]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2017-09-13 10:30:21
 *
 */
@Producer(entityType=UserFriendsAddLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserFriendsAddLogMapper extends BaseMapper<UserFriendsAddLogEntity> {
    
}