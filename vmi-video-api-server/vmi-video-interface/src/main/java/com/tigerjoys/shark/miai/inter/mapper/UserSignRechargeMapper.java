package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserSignRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户签到送话费表[t_user_sign_recharge]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-12-07 15:46:04
 *
 */
@Producer(entityType=UserSignRechargeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserSignRechargeMapper extends BaseMapper<UserSignRechargeEntity> {
    
}