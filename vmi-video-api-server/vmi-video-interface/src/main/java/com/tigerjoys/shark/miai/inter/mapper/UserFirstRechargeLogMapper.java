package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserFirstRechargeLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户首次充值记录[t_user_first_recharge_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-31 15:13:37
 *
 */
@Producer(entityType=UserFirstRechargeLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserFirstRechargeLogMapper extends BaseMapper<UserFirstRechargeLogEntity> {
    
}