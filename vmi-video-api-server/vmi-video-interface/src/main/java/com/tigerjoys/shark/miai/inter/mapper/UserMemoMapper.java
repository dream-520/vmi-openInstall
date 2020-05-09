package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserMemoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户备注表[t_user_memo]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-05-08 11:43:45
 *
 */
@Producer(entityType=UserMemoEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserMemoMapper extends BaseMapper<UserMemoEntity> {
    
}