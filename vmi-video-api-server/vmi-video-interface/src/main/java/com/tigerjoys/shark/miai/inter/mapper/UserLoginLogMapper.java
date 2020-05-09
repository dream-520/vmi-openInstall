package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserLoginLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户登录信息变换记录表[t_user_login_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-06-08 11:57:22
 *
 */
@Producer(entityType=UserLoginLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLogEntity> {
    
}