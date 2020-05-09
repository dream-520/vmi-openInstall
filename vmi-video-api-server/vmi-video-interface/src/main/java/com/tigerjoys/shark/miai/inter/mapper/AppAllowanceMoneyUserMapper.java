package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceMoneyUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  app补助用户记录表[t_app_allowance_money_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-11 17:47:23
 *
 */
@Producer(entityType=AppAllowanceMoneyUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppAllowanceMoneyUserMapper extends BaseMapper<AppAllowanceMoneyUserEntity> {
    
}