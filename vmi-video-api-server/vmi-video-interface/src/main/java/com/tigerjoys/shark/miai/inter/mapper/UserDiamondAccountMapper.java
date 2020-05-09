package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户钻石账户[t_user_diamond_account]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Producer(entityType=UserDiamondAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserDiamondAccountMapper extends BaseMapper<UserDiamondAccountEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserDiamondAccountEntity
	 */
	@Select("select * from t_user_diamond_account where user_id = #{userId} for update")
	public abstract UserDiamondAccountEntity lockByUserId(long userId);
}