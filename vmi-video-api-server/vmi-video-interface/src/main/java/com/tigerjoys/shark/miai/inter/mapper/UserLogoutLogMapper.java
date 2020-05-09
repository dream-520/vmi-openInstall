package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserLogoutLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户账号注销记录表[t_user_logout_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2020-01-06 16:15:35
 *
 */
@Producer(entityType=UserLogoutLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserLogoutLogMapper extends BaseMapper<UserLogoutLogEntity> {
    
}