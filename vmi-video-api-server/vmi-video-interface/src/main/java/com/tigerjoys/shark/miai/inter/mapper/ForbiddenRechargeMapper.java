package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ForbiddenRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户禁用充值入口[t_forbidden_recharge]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2020-03-14 15:08:39
 *
 */
@Producer(entityType=ForbiddenRechargeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ForbiddenRechargeMapper extends BaseMapper<ForbiddenRechargeEntity> {
    
}