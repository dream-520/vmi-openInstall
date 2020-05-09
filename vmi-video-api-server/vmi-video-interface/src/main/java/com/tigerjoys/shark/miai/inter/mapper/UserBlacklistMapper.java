package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserBlacklistEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户黑名单表[t_user_blacklist]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-08-17 11:01:38
 *
 */
@Producer(entityType=UserBlacklistEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserBlacklistMapper extends BaseMapper<UserBlacklistEntity> {
    
}