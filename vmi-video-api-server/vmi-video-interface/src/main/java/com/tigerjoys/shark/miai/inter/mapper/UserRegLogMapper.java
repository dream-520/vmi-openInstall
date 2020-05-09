package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserRegLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户注册信息记录表[t_user_reg_log]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2018-05-02 18:00:10
 *
 */
@Producer(entityType=UserRegLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserRegLogMapper extends BaseMapper<UserRegLogEntity> {
    
}