package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户钻石账户流水[t_user_diamond_account_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Producer(entityType=UserDiamondAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserDiamondAccountLogMapper extends BaseMapper<UserDiamondAccountLogEntity> {
	
}