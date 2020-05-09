package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户余额账户流水[t_user_balance_account_log]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-08-10 11:02:06
 *
 */
@Producer(entityType=UserBalanceAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserBalanceAccountLogMapper extends BaseMapper<UserBalanceAccountLogEntity> {
    
}