package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  app补助用户记录表[t_app_allowance_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-10 17:41:27
 *
 */
@Producer(entityType=AppAllowanceUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppAllowanceUserMapper extends BaseMapper<AppAllowanceUserEntity> {
    
}