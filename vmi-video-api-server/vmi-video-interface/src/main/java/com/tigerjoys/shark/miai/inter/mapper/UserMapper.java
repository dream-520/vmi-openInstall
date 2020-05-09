package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

/**
 * 数据库  用户基础表[t_user]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-04-12 11:26:55
 *
 */
@Producer(entityType=UserEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    
}