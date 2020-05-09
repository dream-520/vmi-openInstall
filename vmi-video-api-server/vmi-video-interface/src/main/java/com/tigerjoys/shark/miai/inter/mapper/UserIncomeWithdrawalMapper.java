package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeWithdrawalEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  提现申请管理表[t_user_income_withdrawal]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Producer(entityType=UserIncomeWithdrawalEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserIncomeWithdrawalMapper extends BaseMapper<UserIncomeWithdrawalEntity> {
    
}