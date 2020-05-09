package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserSpecialRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  特殊用户充值[t_user_special_recharge]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2020-02-26 17:51:20
 *
 */
@Producer(entityType=UserSpecialRechargeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserSpecialRechargeMapper extends BaseMapper<UserSpecialRechargeEntity> {
    
}