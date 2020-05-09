package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserLevelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户等级管理[t_user_level]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-10-30 09:02:26
 *
 */
@Producer(entityType=UserLevelEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserLevelMapper extends BaseMapper<UserLevelEntity> {
    
}