package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPayTimerEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_user_pay_timer]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-01-02 16:25:51
 *
 */
@Producer(entityType=UserPayTimerEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPayTimerMapper extends BaseMapper<UserPayTimerEntity> {
    
}