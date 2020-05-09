package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserSvipEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户充值数据[t_user_svip]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2018-01-26 08:21:25
 *
 */
@Producer(entityType=UserSvipEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserSvipMapper extends BaseMapper<UserSvipEntity> {
    
}