package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserSignCardEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户补签卡账户[t_user_sign_card]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-06 11:49:35
 *
 */
@Producer(entityType=UserSignCardEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserSignCardMapper extends BaseMapper<UserSignCardEntity> {
    
}