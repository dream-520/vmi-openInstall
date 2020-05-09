package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserSignLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户签到记录表[t_user_sign_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-06 11:52:43
 *
 */
@Producer(entityType=UserSignLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserSignLogMapper extends BaseMapper<UserSignLogEntity> {
    
}