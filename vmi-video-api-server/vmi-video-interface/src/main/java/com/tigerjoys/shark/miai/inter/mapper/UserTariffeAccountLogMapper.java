package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户话费账户流水[t_user_tariffe_account_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-12-10 14:00:50
 *
 */
@Producer(entityType=UserTariffeAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTariffeAccountLogMapper extends BaseMapper<UserTariffeAccountLogEntity> {
    
}