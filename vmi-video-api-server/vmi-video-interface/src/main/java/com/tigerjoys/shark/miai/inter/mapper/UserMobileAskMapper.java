package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserMobileAskEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_user_mobile_ask]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-05-16 15:34:24
 *
 */
@Producer(entityType=UserMobileAskEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserMobileAskMapper extends BaseMapper<UserMobileAskEntity> {
    
}