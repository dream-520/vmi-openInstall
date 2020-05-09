package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户SCVC账户流水[t_user_scvc_account_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:24
 *
 */
@Producer(entityType=UserScvcAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserScvcAccountLogMapper extends BaseMapper<UserScvcAccountLogEntity> {
    
}