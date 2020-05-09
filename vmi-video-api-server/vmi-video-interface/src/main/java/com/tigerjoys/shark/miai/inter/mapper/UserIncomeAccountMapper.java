package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户收益账户[t_user_income_account]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Producer(entityType=UserIncomeAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserIncomeAccountMapper extends BaseMapper<UserIncomeAccountEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param user - Long
	 * @return UserIncomeAccountEntity
	 */
	@Select("select * from t_user_income_account where user_id = #{userId} and type = #{type} for update")
	public abstract UserIncomeAccountEntity lockByUserId(@Param("userId") long userId, @Param("type") int type);
}