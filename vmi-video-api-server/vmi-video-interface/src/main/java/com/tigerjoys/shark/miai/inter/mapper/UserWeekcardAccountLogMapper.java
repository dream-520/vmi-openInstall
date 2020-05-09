package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户周卡账户表[t_user_weekcard_account_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-11-14 11:30:48
 *
 */
@Producer(entityType=UserWeekcardAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserWeekcardAccountLogMapper extends BaseMapper<UserWeekcardAccountLogEntity> {
    
}