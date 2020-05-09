package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserExtensionEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户扩展信息表[t_user_extension]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-06-21 10:51:05
 *
 */
@Producer(entityType=UserExtensionEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserExtensionMapper extends BaseMapper<UserExtensionEntity> {
    
}