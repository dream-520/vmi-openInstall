package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserSignControlEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户签到控制[t_user_sign_control]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-09-05 12:01:58
 *
 */
@Producer(entityType=UserSignControlEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserSignControlMapper extends BaseMapper<UserSignControlEntity> {
    
}