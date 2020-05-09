package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户邀请关系表[t_user_invite]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
@Producer(entityType=UserInviteEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserInviteMapper extends BaseMapper<UserInviteEntity> {
    
}