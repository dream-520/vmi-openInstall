package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;

/**
 * 数据库  [t_user_invite_mapping]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
@Producer(entityType=UserInviteMappingEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserInviteMappingMapper extends BaseMapper<UserInviteMappingEntity> {
    

}