package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户积分账户流水[t_user_point_account_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-09-05 11:35:37
 *
 */
@Producer(entityType=UserPointAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPointAccountLogMapper extends BaseMapper<UserPointAccountLogEntity> {
    
}