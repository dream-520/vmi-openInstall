package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountEntity;

/**
 * 数据库  用户余额账户[t_user_balance_account]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-08-10 11:02:06
 *
 */
@Producer(entityType=UserBalanceAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserBalanceAccountMapper extends BaseMapper<UserBalanceAccountEntity> {
	
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserBalanceAccountEntity
	 */
	@Select("select * from t_user_balance_account where userid = #{userId} for update")
	public abstract UserBalanceAccountEntity lockByUserId(long userId);
    
}